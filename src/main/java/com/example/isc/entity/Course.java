package com.example.isc.entity;

import com.example.isc.entity.enumeration.FieldOfStudy;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@SuperBuilder
public class Course extends BaseEntity<Integer> {
    private String name;

    private int unit;

    private FieldOfStudy fieldOfStudy;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "course_teacher",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    @ToString.Exclude
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Student_Course> student_courses;
}
