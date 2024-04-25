package com.example.isc.repository;

import com.example.isc.entity.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository
        extends UserRepository<Admin>{
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Admin a WHERE a.adminId = :adminId AND a.password = :password")
    boolean signIn(@Param("adminId") String studentId, @Param("password") String password);
}
