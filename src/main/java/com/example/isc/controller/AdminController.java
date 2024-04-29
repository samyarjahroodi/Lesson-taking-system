package com.example.isc.controller;

import com.example.isc.entity.Course;
import com.example.isc.mapper.CourseMapper;
import com.example.isc.service.dto.request.CourseDtoRequest;
import com.example.isc.service.dto.response.CourseDtoResponse;
import com.example.isc.service.impl.AdminServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Validated
public class AdminController {
    private final AdminServiceImpl adminService;

    @PostMapping("/enable-student")
    public ResponseEntity<HttpStatus> enableStudent(@RequestParam String studentId) {
        adminService.enableStudent(studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/enable-teacher")
    public ResponseEntity<HttpStatus> enableTeacher(@RequestParam String teacherId) {
        adminService.enableTeacher(teacherId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add-course")
    public ResponseEntity<CourseDtoResponse> addCourse(@RequestBody @Valid CourseDtoRequest courseDtoRequest) {
        Course course = CourseMapper.INSTANCE.requestDtoToModel(courseDtoRequest);
        adminService.addCourse(course);
        CourseDtoResponse courseDtoResponse = CourseMapper.INSTANCE.modelToDto(course);
        return new ResponseEntity<>(courseDtoResponse, HttpStatus.CREATED);
    }

}
