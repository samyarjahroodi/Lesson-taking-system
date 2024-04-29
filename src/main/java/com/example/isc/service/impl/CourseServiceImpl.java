package com.example.isc.service.impl;

import com.example.isc.entity.Course;
import com.example.isc.exception.NullInputException;
import com.example.isc.repository.CourseRepository;
import com.example.isc.service.BaseService;
import com.example.isc.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Transactional
@Service
public class CourseServiceImpl
        implements CourseService, BaseService<Course,Integer> {
    private final CourseRepository courseRepository;

    @Override
    public <S extends Course> S save(S entity) {
        return courseRepository.save(entity);
    }

    @Override
    public Optional<Course> findById(Integer id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> findAllById(Iterable<Integer> integers) {
        return courseRepository.findAllById(integers);
    }

    @Override
    public void deleteById(Integer id) {
        courseRepository.deleteById(id);
    }

    @Override
    public void delete(Course entity) {
        courseRepository.delete(entity);
    }


    @Override
    public void deleteAll() {
        courseRepository.deleteAll();
    }

    @Override
    public Course getReferenceById(Integer integer) {
        return courseRepository.findById(integer).orElseThrow(() -> new NullInputException("null"));
    }

    @Override
    public Course findByName(String name) {
        return courseRepository.findByName(name);
    }
}
