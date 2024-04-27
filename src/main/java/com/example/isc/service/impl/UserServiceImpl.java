package com.example.isc.service.impl;

import com.example.isc.entity.User;
import com.example.isc.exception.NotFoundException;
import com.example.isc.repository.UserRepository;
import com.example.isc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl<T extends User, R extends UserRepository<T>>
        implements UserService {

    protected final R repository;

    protected final BCryptPasswordEncoder passwordEncoder;

    @Override
    public T findByUsername(String username) {
        return repository.findByUsernameIfItsExist(username).orElseThrow(
                () -> new NotFoundException(String.format("USER %s NOT FOUND !", username))
        );
    }

    @Override
    public Optional<T> findByUsernameIfExist(String username) {
        return Optional.ofNullable(repository.findByUsername(username));
    }

}
