package com.example.isc.service.impl;

import com.example.isc.entity.Teacher;
import com.example.isc.exception.NullInputException;
import com.example.isc.repository.TeacherRepository;
import com.example.isc.service.BaseService;
import com.example.isc.service.TeacherService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional(readOnly = true)
@Service
public class TeacherServiceImpl
        extends UserServiceImpl<Teacher, TeacherRepository>
        implements TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository repository, BCryptPasswordEncoder passwordEncoder, TeacherRepository teacherRepository) {
        super(repository, passwordEncoder);
        this.teacherRepository = teacherRepository;
    }

    @Override
    public <S extends Teacher> S save(S entity) {
        return teacherRepository.save(entity);
    }

    @Override
    public Optional<Teacher> findById(Integer id) {
        return teacherRepository.findById(id);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public List<Teacher> findAllById(Iterable<Integer> id) {
        return teacherRepository.findAllById(id);
    }

    @Override
    public void deleteById(Integer id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public void delete(Teacher entity) {
        teacherRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        teacherRepository.deleteAll();
    }

    @Override
    public Teacher getReferenceById(Integer id) {
        return teacherRepository.findById(id).orElseThrow(() -> new NullInputException("null"));
    }
}
