package com.example.isc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Teacher extends User<Integer> {

    String teacherId;

    @Enumerated(EnumType.STRING)
    FieldOfStudy fieldOfStudy;

}
