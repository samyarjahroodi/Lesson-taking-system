package com.example.isc.service.impl;

import com.example.isc.entity.Course;
import com.example.isc.entity.Student;
import com.example.isc.entity.Student_Course;
import com.example.isc.exception.NullInputException;
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
    @Transactional
    public <S extends Student_Course> S save(S entity) {
        return student_courseRepository.save(entity);
    }

    @Override
    public Optional<Student_Course> findById(Integer id) {
        return student_courseRepository.findById(id);
    }

    @Override
    public List<Student_Course> findAll() {
        return student_courseRepository.findAll();
    }

    @Override
    public List<Student_Course> findAllById(Iterable<Integer> id) {
        return student_courseRepository.findAllById(id);
    }

    @Override
    public void deleteById(Integer id) {
        student_courseRepository.deleteById(id);
    }

    @Override
    public void delete(Student_Course entity) {
        student_courseRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        student_courseRepository.deleteAll();
    }

    @Override
    public Student_Course getReferenceById(Integer id) {
        return student_courseRepository.findById(id).orElseThrow(() -> new NullInputException("null"));

    }
}
