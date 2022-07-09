package com.example.be.repository;

import com.example.be.model.ExamClassExaminerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamClassExaminerDetailRepository extends JpaRepository<ExamClassExaminerDetail, Integer> {
}