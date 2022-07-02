package com.example.be.repository;

import com.example.be.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ClassRepository extends JpaRepository<Classes, Long> {
    Iterable<Classes> findByRegistered_examIsNotNull();

    Iterable<Classes> findByRegistered_examIsNull();

    @Modifying
    @Query("update Classes c set c.registered_exam = ?1 where c.id = ?2")
    Classes updateRegisteredClass(Date date, Long id);
}
