package com.example.isc.controller;

import com.example.isc.entity.Student;
import com.example.isc.mapper.StudentMapper;
import com.example.isc.service.dto.request.StudentDtoRequestForRegistration;
import com.example.isc.service.dto.response.StudentDtoResponseToRegistration;
import com.example.isc.service.impl.StudentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
@Validated
public class StudentController {
    private final StudentServiceImpl studentService;

    @PostMapping("/student-registration")
    public ResponseEntity<StudentDtoResponseToRegistration> customerSingUp(@RequestBody @Valid StudentDtoRequestForRegistration dto) {
        Student student = StudentMapper.INSTANCE.requestDtoToModel(dto);
        studentService.studentRegistration(student);
        StudentDtoResponseToRegistration studentDtoResponseToRegistration
                = StudentMapper.INSTANCE.modelToDto(student);
        return new ResponseEntity<>(studentDtoResponseToRegistration, HttpStatus.CREATED);
    }


}
