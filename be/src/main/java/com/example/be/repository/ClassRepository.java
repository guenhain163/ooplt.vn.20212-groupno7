package com.example.be.repository;

import com.example.be.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ClassRepository extends JpaRepository<Classes, Integer> {
    Iterable<Classes> findByRegisteredExamIsNotNull();

    Iterable<Classes> findByRegisteredExamIsNull();

    Iterable<Classes> findByLecturerId(Integer lecturerId);

    @Modifying
    @Query("UPDATE Classes c SET c.registeredExam = ?1 WHERE c.id = ?2")
    Classes updateRegisteredClass(Date date, Integer id);

    @Modifying
    @Query("SELECT a FROM Classes a INNER JOIN ExamClasses b ON b.classId = a.id")
    Optional<Classes> findByExamClassId(Integer examClassId);
}
