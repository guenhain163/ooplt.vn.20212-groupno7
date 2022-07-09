package com.example.be.repository;

import com.example.be.model.ExamClasses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamClassRepository extends JpaRepository<ExamClasses, Integer> {
    Optional<ExamClasses> findByIdAndStatus(Integer id, Integer status);
}