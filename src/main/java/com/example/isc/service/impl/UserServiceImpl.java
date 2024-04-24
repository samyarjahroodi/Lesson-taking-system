package com.example.isc.service.impl;

import com.example.isc.entity.User;
import com.example.isc.repository.UserRepository;
import com.example.isc.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl<T extends User,R extends UserRepository<T>>
        implements UserService {

    protected final R repository;

    protected final BCryptPasswordEncoder passwordEncoder;
}
