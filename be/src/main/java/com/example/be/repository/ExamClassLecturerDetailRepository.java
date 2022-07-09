package com.example.be.repository;

import com.example.be.model.ExamClassLecturerDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamClassLecturerDetailRepository extends JpaRepository<ExamClassLecturerDetail, Long> {
    Optional<ExamClassLecturerDetail> findByExamClassId(Long examClassId);
}