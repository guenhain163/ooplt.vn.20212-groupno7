package exam.management.be.service;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface BaseService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Integer id);

    T save(T t);

    T update(T t);

    void remove(Integer id);
}
