package com.example.isc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Table(name = "\"user\"")
public class User extends BaseEntity<Integer> {

    private String firstname;

    private String lastname;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
