package com.example.be.service;

import com.example.be.model.Classes;
import com.example.be.repository.ClassRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class ClassService  implements BaseService<Classes> {
    @Autowired
    private ClassRepository classRepository;

    @Override
    public Iterable<Classes> findAll() {
        return classRepository.findAll();
    }

    @Override
    public Optional<Classes> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Classes save(Classes classes) {
        return classRepository.save(classes);
    }

    public Classes updateRegisteredClass(Date date, Long id) {
        return classRepository.updateRegisteredClass(date, id);
    }

    @Override
    public void remove(Long id) {

    }

    public Iterable<Classes> findByRegisteredExam(boolean registered_exam) {
        if (registered_exam) {
            return classRepository.findByRegistered_examIsNotNull();
        }
        return classRepository.findByRegistered_examIsNull();
    }
}
