package com.example.be.repository;

import com.example.be.model.ExamClassLecturerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamClassLecturerDetailRepository extends JpaRepository<ExamClassLecturerDetail, Integer> {
    Optional<ExamClassLecturerDetail> findByExamClassId(Integer examClassId);
}