package com.example.be.repository;

import com.example.be.model.ExamClassDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamClassDetailRepository extends JpaRepository<ExamClassDetail, Integer> {
    Optional<ExamClassDetail> findByExamClassId(Integer examClassId);
}