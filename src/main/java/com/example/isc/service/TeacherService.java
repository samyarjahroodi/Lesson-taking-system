package com.example.isc.service;

import com.example.isc.entity.Course;
import com.example.isc.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService
        extends BaseService<Teacher,Integer> {
    List<Course> getCourseList(String teacherId);

}
