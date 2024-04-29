package com.example.isc.service.impl;

import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import com.example.isc.entity.Student_Course;
import com.example.isc.repository.Student_CourseRepository;
import com.example.isc.service.Student_CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class Student_CourseServiceImpl
        implements Student_CourseService {
    private final Student_CourseRepository student_courseRepository;



    @Override
    public List<Student_Course> findAllCoursesByStudent(Student student) {
        return student_courseRepository.findAllByStudent(student);
    }

    @Override
    public List<Student_Course> findAllPassedCoursesByStudent(Student student) {
        return student_courseRepository.findAllPassedCoursesByStudent(student);
    }

    @Override
    public List<Student_Course> findAllNotPassedCoursesByStudent(Student student) {
        return student_courseRepository.findAllNotPassedCoursesByStudent(student);
    }

    @Override
    public Student_Course findByStudentAndCourse(Student student, Course course) {
        return student_courseRepository.findByStudentAndCourse(student, course);
    }

    @Override
    public <S extends Student_Course> S save(S entity) {
        return null;
    }

    @Override
    public Optional<Student_Course> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Student_Course> findAll() {
        return null;
    }

    @Override
    public List<Student_Course> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Student_Course entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Student_Course getReferenceById(Integer integer) {
        return null;
    }
}
