package com.example.isc.service;

import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import com.example.isc.entity.Student_Course;

import java.util.List;
import java.util.Set;


public interface StudentService
        extends BaseUserService<Student> {

    boolean checkIfStudentIsPassedACourse(Student student, Course course);

    double findAverageMarksByStudentId(Student student);

    List<Student_Course> seePassesCoursesByStudentId(Student student);

    void deleteCurrentCourse(Student student, Student_Course student_course);

    Student findByStudentId(String studentId);

    Student studentRegistration(Student student);

    Set<Student_Course> addCourseToStudent(Student student, Course course);


}
