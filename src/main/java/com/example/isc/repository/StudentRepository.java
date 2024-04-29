package com.example.isc.repository;

import com.example.isc.entity.Student;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends BaseUserRepository<Student> {

    @Query("SELECT AVG(sc.mark) FROM Student_Course sc WHERE sc.student = :student")
    Double findAverageMarksByStudentId(Student student);

    Student findByStudentId(String studentId);

}