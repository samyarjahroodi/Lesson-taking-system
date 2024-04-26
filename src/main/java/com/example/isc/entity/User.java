package com.example.isc.entity;

import com.example.isc.entity.enumeration.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@MappedSuperclass
public class User extends BaseEntity<Integer> {

    private String firstname;

    private String lastname;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean isEnabled = false;

    private boolean isBlocked;

    private boolean isExpired;

    private String email;

    private String nationalId;

    private LocalDate creationDate;
}
