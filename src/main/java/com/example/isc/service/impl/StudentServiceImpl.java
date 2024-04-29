package com.example.isc.service.impl;

import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import com.example.isc.entity.Student_Course;
import com.example.isc.entity.enumeration.Role;
import com.example.isc.exception.NotFoundException;
import com.example.isc.exception.NullInputException;
import com.example.isc.repository.StudentRepository;
import com.example.isc.service.StudentService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Pattern;

@Transactional
@Service
@Primary
public class StudentServiceImpl
        extends BaseUserServiceImpl<Student, StudentRepository>
        implements StudentService {
    private final StudentRepository studentRepository;
    private final Student_CourseServiceImpl student_courseService;

    public StudentServiceImpl(StudentRepository repository, BCryptPasswordEncoder passwordEncoder, StudentRepository studentRepository, Student_CourseServiceImpl student_courseService) {
        super(repository, passwordEncoder);
        this.studentRepository = studentRepository;
        this.student_courseService = student_courseService;
    }

    @Override
    public Set<Student_Course> addCourseToStudent(Student student, Course course) {
        if (checkIfStudentIsPassedACourse(student, course)) {
            throw new IllegalArgumentException("Student already passed this course");
        }
        validateStudentAndCourse(student, course);
        validateStudentStatus(student);

        Set<Student_Course> studentCourses = student.getStudent_courses();
        if (studentCourses == null) {
            studentCourses = new HashSet<>();
        }

        List<Student_Course> notPassedCourses = student_courseService.findAllNotPassedCoursesByStudent(student);
        if (notPassedCourses.isEmpty()) {
            Student_Course studentCourse = createStudentCourse(student, course);
            student_courseService.save(studentCourse);
            return Collections.singleton(studentCourse);
        } else {
            int totalUnits = calculateTotalUnits(studentCourses);
            int unitLimit = calculateUnitLimit(findAverageMarksByStudentId(student));
            for (Student_Course c : notPassedCourses) {
                if (isEligibleToAddCourse(c, studentCourses, totalUnits, unitLimit)) {
                    Student_Course studentCourse = createStudentCourse(student, c.getCourse());
                    student_courseService.save(studentCourse);
                }
            }
            return studentCourses;
        }
    }

    private void validateStudentStatus(Student student) {
        if (!student.isEnabled()) {
            throw new IllegalArgumentException("Student is not enabled");
        }
        if (student.isExpired()) {
            throw new IllegalArgumentException("Student is expired");
        }
        if (student.isBlocked()) {
            throw new IllegalArgumentException("Student is blocked");
        }
    }

    private int calculateTotalUnits(Set<Student_Course> studentCourses) {
        return studentCourses.stream().mapToInt(rc -> rc.getCourse().getUnit()).sum();
    }

    private int calculateUnitLimit(double averageMarks) {
        if (averageMarks >= 18) {
            return 24;
        } else if (averageMarks >= 12) {
            return 20;
        } else {
            return 14;
        }
    }

    private boolean isEligibleToAddCourse(Student_Course student_course, Set<Student_Course> studentCourses, int totalUnits, int unitLimit) {
        return studentCourses.stream().noneMatch(rc -> rc.getCourse().equals(student_course)) &&
                (totalUnits + student_course.getCourse().getUnit()) <= unitLimit;
    }

    private Student_Course createStudentCourse(Student student, Course course) {
        Student_Course studentCourse = new Student_Course();
        studentCourse.setCourse(course);
        studentCourse.setStudent(student);
        studentCourse.setCurrentCourse(true);
        student_courseService.save(studentCourse);
        return studentCourse;
    }

    private void validateStudentAndCourse(Student student, Course course) {
        if (student == null || course == null) {
            throw new NullInputException("Student or Course is null");
        }
    }

    @Override
    public boolean checkIfStudentIsPassedACourse(Student student, Course course) {
        List<Student_Course> allPassedCoursesByStudentId
                = student_courseService.findAllPassedCoursesByStudent(student);
        for (Student_Course studentCourse : allPassedCoursesByStudentId) {
            if (studentCourse.getCourse().equals(course) && studentCourse.isPass()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public double findAverageMarksByStudentId(Student student) {
        return studentRepository.findAverageMarksByStudentId(student);
    }

    @Override
    public List<Student_Course> seePassesCoursesByStudentId(Student student) {
        return student_courseService.findAllPassedCoursesByStudent(student);
    }

    @Override
    public void deleteCurrentCourse(Student student, Student_Course student_course) {
        Student_Course existingStudentCourse
                = student_courseService.findByStudentAndCourse(student, student_course.getCourse());

        if (existingStudentCourse != null && existingStudentCourse.equals(student_course)) {
            if (!existingStudentCourse.isPass()) {
                student_courseService.delete(existingStudentCourse);
            }
        } else {
            throw new NotFoundException("Course not found");
        }
    }

    @Transactional
    @Override
    public Student studentRegistration(Student student) {
        checkUsernameAndEmailForRegistration(student);
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setRole(Role.ROLE_STUDENT);
        /*    student.setStudentId(createRandomStudentId());*/
        student.setBlocked(false);
        student.setExpired(false);
        studentRepository.save(student);
        return student;
    }

    @Override
    public Student findByStudentId(String studentId) {
        return repository.findByStudentId(studentId);
    }

    private String createRandomStudentId() {
        String studentId;
        do {
            UUID uuid = UUID.randomUUID();
            String randomId = uuid.toString().replace("-", "");
            studentId = "01" + randomId.substring(0, 6);
        } while (!Pattern.matches("00[0-9]{6}", studentId));
        return studentId;
    }

}
