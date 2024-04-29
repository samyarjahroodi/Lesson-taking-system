package com.example.isc.controller;

import com.example.isc.service.impl.AdminServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Validated
public class AdminController {
    private final AdminServiceImpl adminService;
    private final ModelMapper modelMapper;

    @PostMapping("/enable-student")
    public ResponseEntity<HttpStatus> enableStudent(String studentId) {
        adminService.enableStudent(studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
