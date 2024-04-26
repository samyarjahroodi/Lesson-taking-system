package com.example.isc.service.impl;

import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import com.example.isc.entity.Student_Course;
import com.example.isc.entity.enumeration.Role;
import com.example.isc.exception.DuplicateCourseException;
import com.example.isc.exception.NotFoundException;
import com.example.isc.exception.NullInputException;
import com.example.isc.repository.StudentRepository;
import com.example.isc.service.StudentService;
import com.example.isc.service.dto.request.StudentDtoRequestForRegistration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

@Transactional(readOnly = true)
@Service
public class StudentServiceImpl
        extends UserServiceImpl<Student, StudentRepository>
        implements StudentService {
    private final StudentRepository studentRepository;
    private final Student_CourseServiceImpl student_courseService;


    public StudentServiceImpl(StudentRepository repository, BCryptPasswordEncoder passwordEncoder, StudentRepository studentRepository, Student_CourseServiceImpl student_courseService) {
        super(repository, passwordEncoder);
        this.studentRepository = studentRepository;
        this.student_courseService = student_courseService;
    }

    @Transactional
    public Set<Student_Course> addCourseToStudent(Student student, Course course) {
        checkIfStudentIsPassedACourse(student, course);
        validateStudentAndCourse(student, course);
        validateStudentStatus(student);

        Student studentId = studentRepository.getReferenceById(Integer.valueOf(student.getStudentId()));
        List<Course> notPassedCourses = student_courseService.findAllNotPassedCoursesByStudentId(String.valueOf(studentId));
        Set<Student_Course> studentCourses = student.getStudent_courses();
        int totalUnits = calculateTotalUnits(studentCourses);

        int unitLimit = calculateUnitLimit(findAverageMarksByStudentId(String.valueOf(studentId)));

        for (Course c : notPassedCourses) {
            if (isEligibleToAddCourse(c, studentCourses, totalUnits, unitLimit)) {
                Student_Course studentCourse = createStudentCourse(student, c);
                student_courseService.save(studentCourse);
            }
        }
        return null;
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

    private boolean isEligibleToAddCourse(Course course, Set<Student_Course> studentCourses, int totalUnits, int unitLimit) {
        return !studentCourses.stream().anyMatch(rc -> rc.getCourse().equals(course)) &&
                (totalUnits + course.getUnit()) <= unitLimit;
    }

    private Student_Course createStudentCourse(Student student, Course course) {
        Student_Course studentCourse = new Student_Course();
        studentCourse.setCourse(course);
        studentCourse.setStudents(student);
        studentCourse.setDoesStudentReceive(true);
        return studentCourse;
    }

    private void validateStudentAndCourse(Student student, Course course) {
        if (student == null || course == null) {
            throw new NullInputException("Student or Course is null");
        }
    }

    @Override
    public boolean checkIfStudentIsPassedACourse(Student student, Course course) {
        List<Student_Course> studentCourses = student_courseService.findAllByStudent(student);
        for (Student_Course studentCourse : studentCourses) {
            if (studentCourse.getCourse().equals(course) && studentCourse.isPass()) {
                return true;
            }
        }
        throw new DuplicateCourseException("Duplicate exception");
    }

    @Override
    public double findAverageMarksByStudentId(String studentId) {
        return studentRepository.findAverageMarksByStudentId(studentId);
    }

    @Override
    public List<Course> seePassesCoursesByStudentId(String studentId) {
        return student_courseService.findAllPassedCoursesByStudentId(studentId);
    }

    @Override
    public void deleteCurrentCourse(String studentId, Student_Course student_course) {
        Student_Course existingStudentCourse = student_courseService.findByStudentIdAndCourse(studentId, student_course.getCourse());

        if (existingStudentCourse != null && existingStudentCourse.equals(student_course)) {
            if (!existingStudentCourse.isPass()) {
                student_courseService.delete(existingStudentCourse);
            }
        } else {
            throw new NotFoundException("Course not found");
        }
    }

    @Override
    @Transactional
    public void studentRegistration(StudentDtoRequestForRegistration studentDtoRequestForRegistration) {
        Student student = Student.builder()
                .firstname(studentDtoRequestForRegistration.getFirstname())
                .lastname(studentDtoRequestForRegistration.getLastname())
                .password(passwordEncoder.encode(studentDtoRequestForRegistration.getPassword()))
                .role(Role.ROLE_STUDENT)
                .email(studentDtoRequestForRegistration.getEmail())
                .nationalId(studentDtoRequestForRegistration.getNationalId())
                .isEnabled(false)
                .isBlocked(false)
                .isExpired(false)
                .studentId(createRandomTeacherId())
                .educationDegree(studentDtoRequestForRegistration.getEducationDegree())
                .fieldOfStudy(studentDtoRequestForRegistration.getFieldOfStudy())
                .build();
        studentRepository.save(student);
    }

    @Override
    public Student findByStudentId(String studentId) {
        return findByStudentId(studentId);
    }

    private String createRandomTeacherId() {
        String studentId;
        do {
            UUID uuid = UUID.randomUUID();
            String randomId = uuid.toString().replace("-", "");
            studentId = "01" + randomId.substring(0, 6); // Adjust the substring length as needed
        } while (!Pattern.matches("00[0-9]{6}", studentId));
        return studentId;
    }

    @Override
    public boolean studentSignIn(String studentId, String password) {
        return studentRepository.signIn(studentId, password);
    }

    @Override
    public <S extends Student> S save(S entity) {
        return studentRepository.save(entity);
    }

    @Override
    public Optional<Student> findById(Integer id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findAllById(Iterable<Integer> id) {
        return studentRepository.findAllById(id);
    }

    @Override
    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void delete(Student entity) {
        studentRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        studentRepository.deleteAll();
    }

    @Override
    public Student getReferenceById(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> new NullInputException("null"));
    }
}
