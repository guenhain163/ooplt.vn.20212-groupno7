package com.example.be.service;

import com.example.be.model.ExamClasses;
import com.example.be.repository.ExamClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamClassService implements BaseService<ExamClasses> {
    @Autowired
    private ExamClassRepository examClassRepository;

    @Override
    public Iterable<ExamClasses> findAll() {
        return examClassRepository.findAll();
    }

    @Override
    public Optional<ExamClasses> findById(Long id) {
        return examClassRepository.findById(id);
    }

    @Override
    public ExamClasses save(ExamClasses examClass) {
        return examClassRepository.save(examClass);
    }

    @Override
    public ExamClasses update(ExamClasses examClass) {
        return examClassRepository.saveAndFlush(examClass);
    }

    @Override
    public void remove(Long id) {
        examClassRepository.deleteById(id);
    }
}
