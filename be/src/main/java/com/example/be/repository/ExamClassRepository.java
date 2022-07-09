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
            "d.numberStudent as numberStudent, e.name as nameLecturer) FROM ExamClasses a " +
            "LEFT JOIN Classes b " +
            "   ON a.classId = b.id " +
            "LEFT JOIN Modules c " +
            "   ON b.moduleId = c.id " +
            "LEFT JOIN ExamClassDetail d " +
            "   ON d.examClassId = a.id " +
            "LEFT JOIN Lecturers e " +
            "   ON e.id = b.lecturerId")
    List<Map<String, Object>> getAllExamClasses();
}