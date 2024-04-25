package com.example.isc.entity;

import com.example.isc.entity.enumeration.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@MappedSuperclass
public class User extends BaseEntity<Integer> {

    private String firstname;

    private String lastname;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled = false;

    private boolean isBlocked;

    private boolean isExpired;
}
