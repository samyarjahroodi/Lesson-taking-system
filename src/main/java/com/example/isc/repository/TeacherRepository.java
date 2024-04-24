package com.example.isc.repository;

import com.example.isc.entity.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository
        extends UserRepository<Teacher> {

}
