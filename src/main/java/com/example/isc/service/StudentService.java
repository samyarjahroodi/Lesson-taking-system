package com.example.isc.service;

import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService
        extends BaseService<Student, Integer> {

    boolean checkIfStudentIsPassedACourse(Student student, Course course);

    double findAverageMarksByStudentId(@Param("studentId") String studentId);

    List<Course> seePassesCoursesByStudentId(String studentId);

}
