package com.example.isc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class Teacher extends User{

    private String teacherId;

    @Enumerated(EnumType.STRING)
    private FieldOfStudy fieldOfStudy;

}
