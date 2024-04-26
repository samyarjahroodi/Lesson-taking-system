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

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Teacher t WHERE t.teacherId = :teacherId AND t.password = :password")
    boolean signIn(@Param("teacherId") String teacherId, @Param("password") String password);

    boolean existsByTeacherIdAndPassword(String teacherId, String password);
}
