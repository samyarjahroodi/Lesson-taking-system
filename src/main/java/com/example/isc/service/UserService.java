package com.example.isc.service;

import com.example.isc.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService<T extends User> {
    Optional<T> findByUsernameIfExist(String username);

    T findByUsername(String username);
}
