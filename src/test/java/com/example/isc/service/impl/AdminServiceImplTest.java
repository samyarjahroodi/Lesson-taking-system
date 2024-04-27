package com.example.isc.service.impl;

import com.example.isc.entity.Admin;
import com.example.isc.entity.Student;
import com.example.isc.entity.enumeration.FieldOfStudy;
import com.example.isc.entity.enumeration.StudentDegree;
import com.example.isc.entity.enumeration.TeacherDegree;
import com.example.isc.service.dto.request.StudentDtoRequestForRegistration;
import com.example.isc.service.dto.request.TeacherDtoRequestForRegistration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class AdminServiceImplTest {

    private TeacherServiceImpl teacherService;
    private StudentServiceImpl studentService;
    private AdminServiceImpl adminService;
    private UserServiceImpl userService;

    //creating dto for Teacher
    public TeacherDtoRequestForRegistration createTeacherDto() {
        return TeacherDtoRequestForRegistration.builder()
                .firstname("Samyar")
                .lastname("Jahroodi")
                .password("Password123AVC!@")
                .email("Samyar@gmail.com")
                .nationalId("12345678")
                .fieldOfStudy(FieldOfStudy.MECHANICAL_ENGINEERING)
                .teacherDegree(TeacherDegree.BACHELOR_OF_ELEMENTARY_EDUCATION)
                .username("Samyar")
                .build();
    }

    //creating dto for Student
    public StudentDtoRequestForRegistration createStudentDto() {
        return StudentDtoRequestForRegistration.builder()
                .firstname("Ali")
                .lastname("Rezayi")
                .password("Password123AVC!@")
                .email("Ali@gmail.com")
                .educationDegree(StudentDegree.BACHELOR_DEGREE)
                .nationalId("12355678")
                .fieldOfStudy(FieldOfStudy.MECHANICAL_ENGINEERING)
                .username("Ali")
                .build();
    }


    @BeforeEach
    void setUp() {
        teacherService.teacherRegistration(createTeacherDto());
        studentService.studentRegistration(createStudentDto());
    }

    @AfterEach
    void tearDown() {
        teacherService.deleteAll();
        studentService.deleteAll();
    }


    @Test
    void enableStudent() {
        studentService.studentRegistration(createStudentDto());
        Student samyar = (Student) userService.findByUsername("Samyar");
        adminService.enableStudent(samyar.getStudentId());
        assertEquals(true,samyar.isEnabled());
    }

    @Test
    void enableTeacher() {

    }

    @Test
    void createAdminWhenApplicationRuns() {
    }
}