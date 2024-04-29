package com.example.isc.service.impl;

import com.example.isc.entity.User;
import com.example.isc.exception.DuplicateException;
import com.example.isc.exception.NotFoundException;
import com.example.isc.repository.UserRepository;
import com.example.isc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
public class UserServiceImpl<T extends User, R extends UserRepository<T>>
        implements UserService<T> {
    protected final R repository;

    protected final BCryptPasswordEncoder passwordEncoder;

    @Override
    public T findByUsername(String username) {
        return repository.findByUsernameIfItsExist(username).orElseThrow(
                () -> new NotFoundException(String.format("USER %s NOT FOUND !", username))
        );
    }


    @Override
    public void checkUsernameAndEmailForRegistration(T registration) {
        if (repository.existsByUsername(registration.getUsername()))
            throw new DuplicateException(
                    String.format(
                            "DUPLICATE %s !", registration.getUsername())
            );
        if (repository.existsByEmail(registration.getEmail()))
            throw new DuplicateException(
                    String.format(
                            "DUPLICATE %s !", registration.getEmail())
            );
    }

    @Override
    public Optional<T> findByUsernameIfExist(String username) {
        return Optional.ofNullable(repository.findByUsername(username));
    }

}
