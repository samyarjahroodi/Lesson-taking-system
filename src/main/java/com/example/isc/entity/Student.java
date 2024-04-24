package com.example.isc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
public class Student extends User<Integer>{

    String studentId;

    @Enumerated(EnumType.STRING)
    FieldOfStudy fieldOfStudy;

}
