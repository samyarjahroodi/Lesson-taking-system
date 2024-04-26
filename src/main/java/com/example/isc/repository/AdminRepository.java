package com.example.isc.repository;

import com.example.isc.entity.Admin;
import com.example.isc.entity.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository
        extends UserRepository<Admin> {
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Admin a WHERE a.username = :username AND a.password = :password")
    boolean signIn(@Param("username") String username, @Param("password") String password);

    boolean existsByUsername(String username);

    @Query("SELECT t from Teacher t")
    List<Teacher> findAllTeachers();

    @Query("SELECT s from Student s")
    List<Teacher> findAllStudents();

}
