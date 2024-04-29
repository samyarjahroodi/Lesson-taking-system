package com.example.isc.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
@Setter
@Entity
@ToString
public class Student_Course extends BaseEntity<Integer> {

    @ManyToOne(cascade = CascadeType.MERGE)
    private Student student;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Course course;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Teacher teacher;

    private double mark;

    private int term;

    private boolean isPass;

    private boolean currentCourse;

}
