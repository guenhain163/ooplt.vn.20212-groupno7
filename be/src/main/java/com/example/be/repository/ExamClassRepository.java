package com.example.be.repository;

import com.example.be.model.ExamClasses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ExamClassRepository extends JpaRepository<ExamClasses, Integer> {
    Optional<ExamClasses> findByIdAndStatus(Integer id, Integer status);

    @Query("SELECT new map(a as classExam, c.name as nameModule, c.code as codeModule, " +
            "d.numberStudent as numberStudent) FROM ExamClasses a " +
            "INNER JOIN Classes b " +
            "ON a.classId = b.id " +
            "INNER JOIN Modules c " +
            "ON b.moduleId = c.id " +
            "INNER JOIN ExamClassLecturerDetail d " +
            "ON d.examClassId = a.id")
    List<Map<String, Object>> getAllExamClasses();
}