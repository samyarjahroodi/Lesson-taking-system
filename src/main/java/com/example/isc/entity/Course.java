package com.example.isc.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class Course extends BaseEntity<Integer> {
    private String name;

    private String courseCode;

    private int unit;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "course_teacher",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    @ToString.Exclude
    private List<Teacher> teachers;

 /*   @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    @ToString.Exclude
    private List<Student> students;*/
}
