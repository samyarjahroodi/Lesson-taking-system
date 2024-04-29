package com.example.isc.service;

import com.example.isc.entity.User;

import java.util.Optional;


public interface UserService<T extends User> {
    Optional<T> findByUsernameIfExist(String username);

    T findByUsername(String username);

    void checkUsernameAndEmailForRegistration(T registration);
}
