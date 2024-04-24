package com.example.isc.service.impl;

import com.example.isc.repository.TeacherRepository;
import com.example.isc.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class TeacherServiceImpl
        implements TeacherService {
    private final TeacherRepository teacherRepository;

}
