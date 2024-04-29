package com.example.isc.service;

import com.example.isc.entity.Admin;
import com.example.isc.entity.Course;

public interface AdminService
        extends BaseUserService<Admin> {
    void enableStudent(String studentId);

    void enableTeacher(String teacherId);

    void addCourse(Course course);

}
