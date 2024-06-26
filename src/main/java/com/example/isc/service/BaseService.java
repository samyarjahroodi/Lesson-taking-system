package com.example.isc.service;


import com.example.isc.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<T extends BaseEntity<ID>,ID extends Serializable> {
    <S extends T> S save(S entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    List<T> findAllById(Iterable<ID> ids);

    void deleteById(ID id);

    void delete(T entity);

    void deleteAll();

    T getReferenceById(ID id);
}
