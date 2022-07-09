package com.example.be.repository;

import com.example.be.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@EnableJpaRepositories
public interface ClassRepository extends JpaRepository<Classes, Integer> {
    Iterable<Classes> findByRegisteredExamIsNotNull();

    Iterable<Classes> findByRegisteredExamIsNull();

    @Modifying
    @Query("UPDATE Classes c SET c.registeredExam = ?1 WHERE c.id = ?2")
    Classes updateRegisteredClass(Date date, Integer id);
}
