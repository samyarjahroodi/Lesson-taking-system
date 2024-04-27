package com.example.isc.repository;

import com.example.isc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository<T extends User>
        extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<T> findByUsernameIfItsExist(String username);

    T findByUsername(String username);

}
