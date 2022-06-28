package com.example.be.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    void remove(Long id);
}
