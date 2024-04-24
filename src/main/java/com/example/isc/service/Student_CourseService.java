package com.example.isc.service;

import com.example.isc.entity.Student;
import com.example.isc.entity.Student_Course;

import java.util.List;

public interface Student_CourseService
        extends BaseService<Student_Course,Integer> {
    List<Student_Course> findAllByStudent(Student student);

}
