package com.example.isc.repository;

import com.example.isc.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository
        extends BaseUserRepository<Admin> {
    boolean existsByUsername(String username);
}
