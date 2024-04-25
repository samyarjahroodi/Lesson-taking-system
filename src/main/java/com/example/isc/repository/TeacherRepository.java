package com.example.isc.repository;

import com.example.isc.entity.Course;
import com.example.isc.entity.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository
        extends UserRepository<Teacher> {
    @Query("SELECT c FROM Course c JOIN c.teachers t WHERE t.teacherId = :teacherId")
    List<Course> getCourseList(@Param("teacherId") String teacherId);


}
