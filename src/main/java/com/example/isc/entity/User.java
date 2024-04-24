package com.example.isc.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class User<ID extends Serializable> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected ID id;

    private String firstname;

    private String lastname;

}

