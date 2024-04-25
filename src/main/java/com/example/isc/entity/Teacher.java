package com.example.isc.entity;

import com.example.isc.entity.enumeration.FieldOfStudy;
import com.example.isc.entity.enumeration.TeacherDegree;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class Teacher extends User {

    private String teacherId;

    @Enumerated(EnumType.STRING)
    private FieldOfStudy fieldOfStudy;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Student_Course> studentCourses;


    @ManyToMany(mappedBy = "teachers")
    private List<Course> courses;

    @Enumerated(EnumType.STRING)
    private TeacherDegree teacherDegree;

}
