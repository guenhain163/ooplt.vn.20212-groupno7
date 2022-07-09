package com.example.be.repository;

import com.example.be.model.ExamClasses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamClassRepository extends JpaRepository<ExamClasses, Long> {
}