package com.example.isc.service;

import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import com.example.isc.entity.Student_Course;

import java.util.List;

public interface Student_CourseService
        extends BaseService<Student_Course, Integer> {
    List<Student_Course> findAllCoursesByStudent(Student student);

    List<Student_Course> findAllPassedCoursesByStudent(Student student);

    List<Student_Course> findAllNotPassedCoursesByStudent(Student student);

    Student_Course findByStudentAndCourse(Student student, Course course);

}
