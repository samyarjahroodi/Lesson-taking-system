package com.example.isc.repository;

import com.example.isc.entity.Student;
import com.example.isc.entity.Student_Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Student_CourseRepository
        extends JpaRepository<Student_Course, Integer> {
    @Query("SELECT sc FROM Student_Course sc WHERE sc.students = :student")
    List<Student_Course> findAllByStudent(Student student);

}
