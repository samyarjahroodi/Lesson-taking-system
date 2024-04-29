package com.example.isc.service.impl;

import com.example.isc.entity.*;
import com.example.isc.repository.UserRepository;
import com.example.isc.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl
        extends BaseUserServiceImpl<User, UserRepository>
        implements UserService {

    public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        super(repository, passwordEncoder);
    }
}
