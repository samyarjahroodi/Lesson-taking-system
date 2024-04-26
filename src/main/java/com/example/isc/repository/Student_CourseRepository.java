package com.example.isc.repository;

import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import com.example.isc.entity.Student_Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Student_CourseRepository
        extends JpaRepository<Student_Course, Integer> {
    @Query("SELECT sc FROM Student_Course sc WHERE sc.students = :student")
    List<Student_Course> findAllByStudent(Student student);

    @Query("SELECT sc.course FROM Student_Course sc WHERE sc.students.id = :studentId AND sc.isPass = true")
    List<Course> findAllPassedCoursesByStudentId(@Param("studentId") String studentId);

    @Query("SELECT sc.course FROM Student_Course sc WHERE sc.students.id = :studentId AND sc.isPass = false ")
    List<Course> findAllNotPassedCoursesByStudentId(@Param("studentId") String studentId);

    @Query("SELECT sc FROM Student_Course sc WHERE sc.students = :student AND sc.course = :course")
    Student_Course findByStudentAndCourse(@Param("student") Student student, @Param("course") Course course);

    @Query("SELECT sc FROM Student_Course sc WHERE sc.students.id = :studentId AND sc.course = :course")
    Student_Course findByStudentIdAndCourse(@Param("studentId") String studentId, @Param("course") Course course);
}
