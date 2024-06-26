package com.example.isc.service.impl;

import com.example.isc.entity.BaseEntity;
import com.example.isc.exception.NullInputException;
import com.example.isc.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
public abstract class BaseServiceImpl<T extends BaseEntity<ID>, ID extends Serializable, R extends JpaRepository<T, ID>>
        implements BaseService<T, ID> {

    final protected R repository;

    @Override
    public <S extends T> S save(S entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public T getReferenceById(ID id) {
        return repository.findById(id).orElseThrow(() -> new NullInputException("null"));
    }
}
