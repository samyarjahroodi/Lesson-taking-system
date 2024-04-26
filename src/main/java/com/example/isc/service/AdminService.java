package com.example.isc.service;

import com.example.isc.entity.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService
        extends BaseService<Admin, Integer> {
    boolean signIn(String username, String password);

    void enableStudent(String studentId);

    void enableTeacher(String teacherId);

}
