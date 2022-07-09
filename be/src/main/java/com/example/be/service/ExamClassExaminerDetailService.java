package com.example.be.service;

import com.example.be.model.ExamClassExaminerDetail;
import com.example.be.repository.ExamClassExaminerDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamClassExaminerDetailService implements BaseService<ExamClassExaminerDetail> {
    @Autowired
    private ExamClassExaminerDetailRepository examClassExaminerDetailRepository;

    @Override
    public Iterable<ExamClassExaminerDetail> findAll() {
        return examClassExaminerDetailRepository.findAll();
    }

    @Override
    public Optional<ExamClassExaminerDetail> findById(Integer id) {
        return examClassExaminerDetailRepository.findById(id);
    }

    @Override
    public ExamClassExaminerDetail save(ExamClassExaminerDetail examClassExaminerDetail) {
        return examClassExaminerDetailRepository.save(examClassExaminerDetail);
    }

    @Override
    public ExamClassExaminerDetail update(ExamClassExaminerDetail examClassExaminerDetail) {
        return examClassExaminerDetailRepository.saveAndFlush(examClassExaminerDetail);
    }

    @Override
    public void remove(Integer id) {
        examClassExaminerDetailRepository.deleteById(id);
    }
}