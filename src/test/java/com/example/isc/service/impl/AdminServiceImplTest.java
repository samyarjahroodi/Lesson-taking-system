package com.example.isc.service.impl;

import com.example.isc.entity.Student;
import com.example.isc.entity.Teacher;
import com.example.isc.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ComponentScan(basePackages = "com.example.isc.")
class AdminServiceImplTest {

    @Autowired
    private TeacherServiceImpl teacherService;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        Student student = new Student();
        student.setUsername("Samyar");
        studentService.save(student);

        Teacher teacher = new Teacher();
        teacher.setUsername("Ali");
        teacherService.save(teacher);
    }

    @AfterEach
    void tearDown() {
        teacherService.deleteAll();
        studentService.deleteAll();
    }


    @Test
    void enableStudent() {
        Student samyar = (Student) userService.findByUsername("Samyar");
        adminService.enableStudent(samyar.getStudentId());
        assertTrue(samyar.isEnabled());
    }

    @Test
    void enableTeacher() {
        Teacher ali = (Teacher) userService.findByUsername("Ali");
        adminService.enableTeacher(ali.getTeacherId());
        assertTrue(ali.isEnabled());
    }

    @Test
    void createAdminWhenApplicationRuns() {
        User admin = userService.findByUsername("admin");
        assertEquals("admin",admin.getUsername());
    }

/*    @Test
    void createAdminWhenApplicationRunsToCheckIfAdminIsNotExisted() {
        User admin = userService.findByUsername("admin");
        assertEquals("admin",admin.getUsername());
    }*/


}