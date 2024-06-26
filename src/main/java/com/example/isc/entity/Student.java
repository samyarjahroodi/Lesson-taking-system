package com.example.isc.entity;

import com.example.isc.entity.enumeration.FieldOfStudy;
import com.example.isc.entity.enumeration.StudentDegree;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SuperBuilder
@ToString
public class Student extends User {
    private String studentId;

    @Enumerated(EnumType.STRING)
    private StudentDegree educationDegree;

    @Enumerated(EnumType.STRING)
    private FieldOfStudy fieldOfStudy;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Student_Course> student_courses;



}
