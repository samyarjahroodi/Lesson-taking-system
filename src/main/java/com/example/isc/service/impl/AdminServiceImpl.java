package com.example.isc.service.impl;

import com.example.isc.entity.Admin;
import com.example.isc.exception.NullInputException;
import com.example.isc.repository.AdminRepository;
import com.example.isc.service.AdminService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

public class AdminServiceImpl
        extends UserServiceImpl<Admin, AdminRepository>
        implements AdminService {

    public AdminServiceImpl(AdminRepository repository, BCryptPasswordEncoder passwordEncoder) {
        super(repository, passwordEncoder);
    }

    @Override
    public <S extends Admin> S save(S entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<Admin> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Admin> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Admin> findAllById(Iterable<Integer> id) {
        return repository.findAllById(id);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(Admin entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Admin getReferenceById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NullInputException("null"));
    }
}
