package com.example.isc.service.impl;

import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import com.example.isc.entity.Student_Course;
import com.example.isc.entity.Teacher;
import com.example.isc.entity.enumeration.Role;
import com.example.isc.exception.ExpireDateException;
import com.example.isc.repository.TeacherRepository;
import com.example.isc.service.TeacherService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;


@Transactional
@Service
public class TeacherServiceImpl
        extends BaseUserServiceImpl<Teacher, TeacherRepository>
        implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final Student_CourseServiceImpl student_courseService;


    public TeacherServiceImpl(TeacherRepository repository, BCryptPasswordEncoder passwordEncoder,
                              TeacherRepository teacherRepository, Student_CourseServiceImpl student_courseService) {
        super(repository, passwordEncoder);
        this.teacherRepository = teacherRepository;
        this.student_courseService = student_courseService;
    }

    private String createRandomTeacherId() {
        String teacherId;
        do {
            UUID uuid = UUID.randomUUID();
            String randomId = uuid.toString().replace("-", "");
            teacherId = "00" + randomId.substring(0, 6); // Adjust the substring length as needed
        } while (!Pattern.matches("00[0-9]{6}", teacherId));
        return teacherId;
    }


    @Transactional
    public void giveMarkToStudent(Student student, Course course, double mark, int term) {
        Student_Course studentCourse = student_courseService.findByStudentAndCourse(student, course);
        checkTeacherStatus(studentCourse.getTeacher());
        if (mark > 20 && term < 1) {
            throw new IllegalArgumentException("Mark must be between 0 and 20 Or term should be bigger than 1");
        }

        if (studentCourse != null) {
            studentCourse.setMark(mark);
            studentCourse.setTerm(term);
            studentCourse.setPass(mark >= 10);
            System.out.println("Before saving: " + studentCourse);
            student_courseService.save(studentCourse);
            System.out.println("After saving: " + studentCourse);
        } else {
            throw new IllegalStateException("Student is not enrolled in the course");
        }
    }


    public boolean checkTeacherStatus(Teacher teacher) {
        return !teacher.isEnabled() && !teacher.isBlocked() && !teacher.isExpired();
    }


    @Override
    public Teacher teacherRegistration(Teacher teacher) {
        checkUsernameAndEmailForRegistration(teacher);
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        teacher.setRole(Role.ROLE_TEACHER);
        teacher.setTeacherId(createRandomTeacherId());
        teacher.setBlocked(false);
        teacher.setExpired(false);
        teacherRepository.save(teacher);
        return teacher;
    }


    @Override
    public Teacher findByTeacherId(String teacherId) {
        return repository.findByTeacherId(teacherId);
    }

    public boolean checkIfTeacherIsNotExpired(Teacher teacher) {
        if (LocalDate.now().isAfter(teacher.getCreationDate().plusYears(4))) {
            throw new ExpireDateException("Teacher's creation date is expired");
        }
        return true;
    }

    @Override
    public List<Course> getCourseList(String teacherId) {
        return repository.getCourseList(teacherId);
    }
}
