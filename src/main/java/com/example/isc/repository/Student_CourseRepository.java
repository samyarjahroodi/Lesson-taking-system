package com.example.isc.repository;

import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import com.example.isc.entity.Student_Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Student_CourseRepository
        extends JpaRepository<Student_Course, Integer> {
    @Query("SELECT sc FROM Student_Course sc WHERE sc.students = :student")
    List<Student_Course> findAllByStudent(Student student);

    @Query("SELECT sc FROM Student_Course sc WHERE sc.students = :student AND sc.isPass = true")
    List<Student_Course> findAllPassedCoursesByStudent(Student student);

    @Query("SELECT sc FROM Student_Course sc WHERE sc.students = :student AND sc.isPass = false")
    List<Student_Course> findAllNotPassedCoursesByStudent(Student student);

    @Query("SELECT sc FROM Student_Course sc WHERE sc.students = :student AND sc.course = :course")
    Student_Course findByStudentAndCourse(@Param("student") Student student, @Param("course") Course course);

}
