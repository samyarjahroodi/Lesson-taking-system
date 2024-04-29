package com.example.isc.service;

import com.example.isc.entity.Course;
import org.springframework.stereotype.Service;


public interface CourseService extends BaseService<Course, Integer> {
    Course findByName(String name);
}
