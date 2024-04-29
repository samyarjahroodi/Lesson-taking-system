package com.example.isc.service.impl;

import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import com.example.isc.entity.Student_Course;
import com.example.isc.entity.Teacher;
import com.example.isc.entity.enumeration.Role;
import com.example.isc.exception.ExpireDateException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ComponentScan(basePackages = "com.example.isc.")
class TeacherServiceImplTest {


    @Autowired
    private Student_CourseServiceImpl student_courseService;

    @Autowired
    private TeacherServiceImpl teacherService;

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @AfterEach
    void tearDown() {
        teacherService.deleteAll();
        student_courseService.deleteAll();
        studentService.deleteAll();
        courseService.deleteAll();
    }

    @Test
    void giveMarkToStudent() {
        Teacher teacher = new Teacher();
        teacher.setUsername("teacher1");
        teacher.setPassword("password");
        teacher.setRole(Role.ROLE_TEACHER);
        teacher.setEnabled(true);
        teacher.setBlocked(false);
        teacher.setExpired(false);
        teacher.setCreationDate(LocalDate.now().minusYears(2));
        teacherService.save(teacher);

        Student student = new Student();
        student.setUsername("student1");
        studentService.save(student);

        Course course = new Course();
        course.setName("Math");
        courseService.save(course);

        Student_Course student_course = new Student_Course();
        student_course.setStudent(student);
        student_course.setCourse(course);
        student_course.setTeacher(teacher);
        student_courseService.save(student_course);
        teacherService.giveMarkToStudent(student, course, 19.0, 1);

        assertDoesNotThrow(() -> teacherService.giveMarkToStudent(student, course, 19.0, 1));
    }

    @Test
    void checkTeacherStatus_Enabled_NotBlocked_NotExpired_ReturnsTrue() {
        Teacher teacher = new Teacher();
        teacher.setEnabled(true);
        teacher.setBlocked(false);
        teacher.setExpired(false);
        teacherService.save(teacher);

        boolean status = teacherService.checkTeacherStatus(teacher);

        assertFalse(status);
    }

    @Test
    void checkTeacherStatus_Disabled_ReturnsFalse() {
        Teacher teacher = new Teacher();
        teacher.setEnabled(false);
        teacher.setBlocked(false);
        teacher.setExpired(false);
        teacherService.save(teacher);

        boolean status = teacherService.checkTeacherStatus(teacher);

        assertFalse(status);
    }

    @Test
    void checkTeacherStatus_Blocked_ReturnsFalse() {
        Teacher teacher = new Teacher();
        teacher.setEnabled(true);
        teacher.setBlocked(true);
        teacher.setExpired(false);
        teacherService.save(teacher);

        boolean status = teacherService.checkTeacherStatus(teacher);

        assertFalse(status);
    }

    @Test
    void checkTeacherStatus_Expired_ReturnsFalse() {
        Teacher teacher = new Teacher();
        teacher.setEnabled(true);
        teacher.setBlocked(false);
        teacher.setExpired(true);

        boolean status = teacherService.checkTeacherStatus(teacher);

        assertFalse(status);
    }

    @Test
    void teacherRegistration_ValidTeacher_ReturnsTeacher() {
        Teacher teacher = new Teacher();
        teacher.setUsername("testTeacher");
        teacher.setEmail("test@example.com");
        teacher.setPassword("password");

        Teacher registeredTeacher = teacherService.teacherRegistration(teacher);

        assertNotNull(registeredTeacher);
        assertNotNull(registeredTeacher.getTeacherId());
        assertEquals("testTeacher", registeredTeacher.getUsername());
        assertEquals("test@example.com", registeredTeacher.getEmail());
        assertTrue(passwordEncoder.matches("password", registeredTeacher.getPassword()));
        assertEquals(Role.ROLE_TEACHER, registeredTeacher.getRole());
        assertFalse(registeredTeacher.isBlocked());
        assertFalse(registeredTeacher.isExpired());


    }

    @Test
    void findByTeacherId() {
        Teacher expectedTeacher = new Teacher();
        expectedTeacher.setTeacherId("123456");
        expectedTeacher.setUsername("testTeacher");
        expectedTeacher.setEmail("test@example.com");
        teacherService.save(expectedTeacher);

        Teacher foundTeacher = teacherService.findByTeacherId("123456");

        assertNotNull(foundTeacher);
        assertEquals(expectedTeacher.getTeacherId(), foundTeacher.getTeacherId());
        assertEquals(expectedTeacher.getUsername(), foundTeacher.getUsername());
        assertEquals(expectedTeacher.getEmail(), foundTeacher.getEmail());
    }

    @Test
    void checkIfTeacherIsNotExpired_CreationDateWithinFourYears_ReturnsTrue() {

        Teacher teacher = new Teacher();
        teacher.setCreationDate(LocalDate.now().minusYears(3));
        teacherService.save(teacher);

        boolean result = teacherService.checkIfTeacherIsNotExpired(teacher);

        assertTrue(result);
    }

    @Test
    void checkIfTeacherIsNotExpired_CreationDateExactlyFourYearsAgo_ReturnsTrue() {
        Teacher teacher = new Teacher();
        teacher.setCreationDate(LocalDate.now().minusYears(4));
        teacherService.save(teacher);

        boolean result = teacherService.checkIfTeacherIsNotExpired(teacher);

        assertTrue(result);
    }

    @Test
    void checkIfTeacherIsNotExpired_CreationDateMoreThanFourYearsAgo_ThrowsExpireDateException() {

        Teacher teacher = new Teacher();
        teacher.setCreationDate(LocalDate.now().minusYears(5));
        teacherService.save(teacher);


        assertThrows(ExpireDateException.class, () -> teacherService.checkIfTeacherIsNotExpired(teacher));
    }
}