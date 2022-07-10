package com.example.be.repository;

import com.example.be.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ClassRepository extends JpaRepository<Classes, Integer> {
    Iterable<Classes> findByRegisteredExamIsNotNull();

    Iterable<Classes> findByRegisteredExamIsNull();

    Iterable<Classes> findByLecturerId(Integer lecturerId);

    @Query("SELECT NEW map(a.code as code, b as examClass, c.name as nameModule, c.code as codeModule) FROM ExamClasses b " +
            "LEFT JOIN Classes a ON b.classId = a.id " +
            "LEFT JOIN Modules c ON c.id = a.moduleId " +
            "WHERE b.semester = ?1")
    List<Map<String, Object>> findBySemester(String semester);

    @Modifying
    @Query("UPDATE Classes c SET c.registeredExam = ?1 WHERE c.id = ?2")
    Classes updateRegisteredClass(Date date, Integer id);

    @Query("SELECT a FROM Classes a INNER JOIN ExamClasses b ON b.classId = a.id WHERE b.id = ?1")
    Optional<Classes> findByExamClassId(Integer examClassId);
}
