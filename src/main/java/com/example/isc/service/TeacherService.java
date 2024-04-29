package com.example.isc.service;

import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import com.example.isc.entity.Teacher;

import java.util.List;


public interface TeacherService
        extends BaseUserService<Teacher> {
    List<Course> getCourseList(String teacherId);

    Teacher teacherRegistration(Teacher teacher);

    Teacher findByTeacherId(String teacherId);

    void giveMarkToStudent(Student student, Course course, double mark, int term);

    boolean checkTeacherStatus(Teacher teacher);

}
