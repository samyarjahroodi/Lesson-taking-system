package com.example.isc.service.impl;


import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import com.example.isc.entity.Student_Course;
import com.example.isc.entity.enumeration.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ComponentScan(basePackages = "com.example.isc.")
class StudentServiceImplTest {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private Student_CourseServiceImpl studentCourseService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        studentService.deleteAll();
        courseService.deleteAll();
    }

    @Test
    void addCourseToStudent() {
        Student student = new Student();
        student.setUsername("Reza");
        studentService.save(student);

        Course course = new Course();
        course.setName("Mathematics");
        course.setUnit(3);
        courseService.save(course);

        Set<Student_Course> studentCourses = studentService.addCourseToStudent(student, course);

        assertEquals(1, studentCourses.size());
        assertTrue(studentCourses.stream().anyMatch(sc -> sc.getCourse().equals(course)));
    }

    @Test
    void checkIfStudentIsPassedACourse() {
        Student student = new Student();
        student.setUsername("Reza");
        studentService.save(student);

        Course course = new Course();
        course.setName("Physics");
        courseService.save(course);

        Student_Course studentCourse = new Student_Course();
        studentCourse.setStudent(student);
        studentCourse.setCourse(course);
        studentCourse.setPass(true);
        studentCourseService.save(studentCourse);

        assertTrue(studentService.checkIfStudentIsPassedACourse(student, course));
    }

    @Test
    void findAverageMarksByStudentId() {
        Student student = new Student();
        student.setUsername("Reza");
        studentService.save(student);

        Course course = new Course();
        course.setName("Physics");
        courseService.save(course);

        Student_Course studentCourse = new Student_Course();
        studentCourse.setStudent(student);
        studentCourse.setCourse(course);
        studentCourse.setMark(12.3);
        studentCourse.setPass(true);
        studentCourseService.save(studentCourse);

        assertEquals(12.3, studentService.findAverageMarksByStudentId(student));
    }

    @Test
    void deleteCurrentCourse() {
        Student student = new Student();
        student.setUsername("Reza");
        studentService.save(student);

        Course course = new Course();
        course.setName("Physics");
        courseService.save(course);

        Student_Course studentCourse = new Student_Course();
        studentCourse.setStudent(student);
        studentCourse.setCourse(course);
        studentCourse.setCurrentCourse(true);
        studentCourse.setMark(12.3);
        studentCourseService.save(studentCourse);


        assertEquals(12.3, studentService.findAverageMarksByStudentId(student));


    }

    @Test
    public void testStudentRegistration() {
        Student student = new Student();
        student.setUsername("samyar");
        student.setPassword("testpassword");
        student.setEmail("test@example.com");

        Student registeredStudent = studentService.studentRegistration(student);

        assertNotNull(registeredStudent);

        assertTrue(passwordEncoder.matches("testpassword", registeredStudent.getPassword()));

        assertEquals(Role.ROLE_STUDENT, registeredStudent.getRole());
        assertFalse(registeredStudent.isBlocked());
        assertFalse(registeredStudent.isExpired());
    }

    @Test
    void findByStudentId() {
        Student student = new Student();
        student.setStudentId("123");
        student.setUsername("testStudent");
        studentService.save(student);
        Student foundStudent = studentService.findByStudentId("123");
        assertEquals("testStudent", foundStudent.getUsername());
    }
}
