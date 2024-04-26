package com.example.isc.repository;

import com.example.isc.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository
        extends UserRepository<Student> {
    @Query("SELECT AVG(sc.mark) FROM Student_Course sc WHERE sc.students.id = :studentId")
    Double findAverageMarksByStudentId(@Param("studentId") String studentId);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Student s WHERE s.studentId = :teacherId AND s.password = :password")
    boolean signIn(@Param("teacherId") String studentId, @Param("password") String password);

    Student findByStudentId(String studentId);


}
