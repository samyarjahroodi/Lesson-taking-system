package com.example.isc.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Student extends User {
    private String studentId;

    @Enumerated(EnumType.STRING)
    private FieldOfStudy fieldOfStudy;

}
