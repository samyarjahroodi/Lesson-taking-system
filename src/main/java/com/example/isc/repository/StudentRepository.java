package com.example.isc.repository;

import com.example.isc.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository
        extends UserRepository<Student> {

}
