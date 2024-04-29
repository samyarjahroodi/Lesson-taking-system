package com.example.isc.service.impl;

import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import com.example.isc.entity.Student_Course;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ComponentScan(basePackages = "com.example.isc.")
@Transactional
class StudentServiceImplTest {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private Student_CourseServiceImpl studentCourseService;

    @BeforeEach
    void setUp() {
        // Add setup code here if needed before each test method
    }

    @AfterEach
    void tearDown() {
        studentService.deleteAll();
        courseService.deleteAll();
    }

    @Test
    void addCourseToStudent() {
        Student student = new Student();
        student.setUsername("JohnDoe");
        studentService.save(student);

        Course course = new Course();
        course.setName("Mathematics");
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
        studentCourse.setStudents(student);
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
        studentCourse.setStudents(student);
        studentCourse.setCourse(course);
        studentCourse.setMark(12.3);
        studentCourse.setPass(true);
        studentCourseService.save(studentCourse);

        assertEquals(12.3,studentService.findAverageMarksByStudentId(student));
    }

    @Test
    void deleteCurrentCourse() {

    }

    @Test
    void studentRegistration() {
        // Test implementation here
    }

    @Test
    void findByStudentId() {
        // Test implementation here
    }
}
