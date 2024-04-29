package com.example.isc.service;

import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import com.example.isc.entity.Student_Course;
import com.example.isc.service.dto.request.StudentDtoRequestForRegistration;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService
        extends BaseService<Student, Integer> {

    boolean checkIfStudentIsPassedACourse(Student student, Course course);

    double findAverageMarksByStudentId(Student student);

    List<Student_Course> seePassesCoursesByStudentId(Student student);

    void deleteCurrentCourse(Student student, Student_Course student_course);

    Student findByStudentId(String studentId);

    Student studentRegistration(Student student);


}
