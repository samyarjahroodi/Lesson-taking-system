package com.example.isc.service.impl;

import com.example.isc.entity.Admin;
import com.example.isc.repository.AdminRepository;
import com.example.isc.service.AdminService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AdminServiceImpl
        extends UserServiceImpl<Admin, AdminRepository>
        implements AdminService {

    public AdminServiceImpl(AdminRepository repository, BCryptPasswordEncoder passwordEncoder) {
        super(repository, passwordEncoder);
    }

}
