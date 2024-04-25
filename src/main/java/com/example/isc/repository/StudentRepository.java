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
}
