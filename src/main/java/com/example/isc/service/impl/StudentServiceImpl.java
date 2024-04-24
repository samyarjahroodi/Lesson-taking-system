package com.example.isc.service.impl;

import com.example.isc.repository.StudentRepository;
import com.example.isc.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class StudentServiceImpl
        implements StudentService {
    private final StudentRepository studentRepository;

}
