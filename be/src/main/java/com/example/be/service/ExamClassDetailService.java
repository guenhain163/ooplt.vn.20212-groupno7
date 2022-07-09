package com.example.be.service;

import com.example.be.model.ExamClassDetail;
import com.example.be.repository.ExamClassDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamClassDetailService implements BaseService<ExamClassDetail> {
    @Autowired
    private ExamClassDetailRepository examClassDetailRepository;

    @Override
    public Iterable<ExamClassDetail> findAll() {
        return examClassDetailRepository.findAll();
    }

    @Override
    public Optional<ExamClassDetail> findById(Integer id) {
        return examClassDetailRepository.findById(id);
    }

    @Override
    public ExamClassDetail save(ExamClassDetail examClassDetail) {
        return examClassDetailRepository.save(examClassDetail);
    }

    @Override
    public ExamClassDetail update(ExamClassDetail examClassDetail) {
        return examClassDetailRepository.saveAndFlush(examClassDetail);
    }

    @Override
    public void remove(Integer id) {
        examClassDetailRepository.deleteById(id);
    }

    public Optional<ExamClassDetail> findByExamClassId(Integer examClassId) {
        return examClassDetailRepository.findByExamClassId(examClassId);
    }
}
