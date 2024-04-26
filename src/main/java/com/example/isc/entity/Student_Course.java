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

    @ManyToOne(cascade = CascadeType.ALL)
    private Student students;

    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;

    private int mark;

    private int term;

    private boolean isPass;

    private boolean doesStudentReceive;

}
