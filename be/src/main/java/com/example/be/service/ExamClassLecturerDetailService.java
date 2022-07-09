package com.example.be.service;

import com.example.be.model.ExamClassLecturerDetail;
import com.example.be.repository.ExamClassLecturerDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamClassLecturerDetailService implements BaseService<ExamClassLecturerDetail> {
    @Autowired
    private ExamClassLecturerDetailRepository examClassLecturerDetailRepository;

    @Override
    public Iterable<ExamClassLecturerDetail> findAll() {
        return examClassLecturerDetailRepository.findAll();
    }

    @Override
    public Optional<ExamClassLecturerDetail> findById(Integer id) {
        return examClassLecturerDetailRepository.findById(id);
    }

    @Override
    public ExamClassLecturerDetail save(ExamClassLecturerDetail examClassLecturerDetail) {
        return examClassLecturerDetailRepository.save(examClassLecturerDetail);
    }

    @Override
    public ExamClassLecturerDetail update(ExamClassLecturerDetail examClassLecturerDetail) {
        return examClassLecturerDetailRepository.saveAndFlush(examClassLecturerDetail);
    }

    @Override
    public void remove(Integer id) {
        examClassLecturerDetailRepository.deleteById(id);
    }

    public Optional<ExamClassLecturerDetail> findByExamClassId(Integer examClassId) {
        return examClassLecturerDetailRepository.findByExamClassId(examClassId);
    }
}
