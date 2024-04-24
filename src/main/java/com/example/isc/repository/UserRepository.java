package com.example.isc.repository;

import com.example.isc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository <T extends User>
        extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {

}
