package com.example.isc.service.impl;

import com.example.isc.entity.Student;
import com.example.isc.exception.NullInputException;
import com.example.isc.repository.StudentRepository;
import com.example.isc.service.BaseService;
import com.example.isc.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class StudentServiceImpl
        extends UserServiceImpl<Student, StudentRepository>
        implements StudentService, BaseService<Student, Integer> {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository repository, BCryptPasswordEncoder passwordEncoder, StudentRepository studentRepository) {
        super(repository, passwordEncoder);
        this.studentRepository = studentRepository;
    }

    @Override
    public <S extends Student> S save(S entity) {
        return studentRepository.save(entity);
    }

    @Override
    public Optional<Student> findById(Integer id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findAllById(Iterable<Integer> id) {
        return studentRepository.findAllById(id);
    }

    @Override
    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void delete(Student entity) {
        studentRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        studentRepository.deleteAll();
    }

    @Override
    public Student getReferenceById(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> new NullInputException("null"));
    }
}
