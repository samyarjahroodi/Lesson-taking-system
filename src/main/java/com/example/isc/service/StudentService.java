package com.example.isc.service;

import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentService
        extends BaseService<Student,Integer>{

    boolean checkIfStudentIsPassedACourse(Student student,Course course);

}
