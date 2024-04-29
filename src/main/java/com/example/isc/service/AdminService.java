package com.example.isc.service;

import com.example.isc.entity.Admin;
import org.springframework.stereotype.Service;


public interface AdminService
        extends BaseService<Admin, Integer> {
    void enableStudent(String studentId);

    void enableTeacher(String teacherId);

}
