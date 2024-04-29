package com.example.isc.service;

import com.example.isc.entity.Course;


public interface CourseService extends BaseService<Course, Integer> {
    Course findByName(String name);
}
