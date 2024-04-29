package com.example.isc.service.impl;

import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import com.example.isc.entity.Student_Course;
import com.example.isc.entity.Teacher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ComponentScan(basePackages = "com.example.isc.")
class Student_CourseServiceImplTest {
    @Autowired
    private TeacherServiceImpl teacherService;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private Student_CourseServiceImpl student_courseService;

    @Autowired
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        Student student = new Student();
        student.setUsername("Samyar");
        student.setStudentId("12345");
        studentService.save(student);

        Teacher teacher = new Teacher();
        teacher.setUsername("Ali");
        teacherService.save(teacher);

        Course course = new Course();
        course.setName("vibration");
        courseService.save(course);

        Student_Course student_course = new Student_Course();
        student_course.setStudents(student);
        student_course.setCourse(course);


        student_courseService.save(student_course);
    }

    @AfterEach
    void tearDown() {
        teacherService.deleteAll();
        studentService.deleteAll();
        student_courseService.deleteAll();
    }


    @Test
    void findAllPassedCoursesByStudentId() {
        Student samyar = studentService.findByUsername("Samyar");

        List<Student_Course> passedCourses = student_courseService.findAllPassedCoursesByStudent(samyar);

        assertEquals(0, passedCourses.size());

        Course course = new Course();
        course.setName("Mathematics");
        courseService.save(course);

        Student_Course studentCourse = new Student_Course();
        studentCourse.setStudents(samyar);
        studentCourse.setCourse(course);
        studentCourse.setPass(true);
        student_courseService.save(studentCourse);

        passedCourses = student_courseService.findAllPassedCoursesByStudent(samyar);

        assertEquals(1, passedCourses.size());
    }

    @Test
    void findAllNotPassedCoursesByStudentId() {
        Student samyar = studentService.findByUsername("Samyar");

        List<Student_Course> notPassedCourses =
                student_courseService.findAllNotPassedCoursesByStudent(samyar);

        assertEquals(1, notPassedCourses.size());
    }

    @Test
    void findByStudentAndCourse() {
        Student samyar = studentService.findByUsername("Samyar");

        Course course = courseService.findByName("vibration");

        Student_Course studentCourse = student_courseService.findByStudentAndCourse(samyar, course);

        assertNotNull(studentCourse);
    }


}