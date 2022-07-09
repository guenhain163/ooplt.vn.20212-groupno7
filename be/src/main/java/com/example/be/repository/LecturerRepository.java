package com.example.be.repository;

import com.example.be.model.Lecturers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturers, Integer > {
    List<Lecturers> findByRoleIn(List<Integer> roles);

    Optional<Lecturers> findByIdAndRoleIn(Integer  id, List<Integer> roles);

    @Modifying
    @Query("SELECT a FROM Lecturers a\n" +
            "\tWHERE a.id NOT IN\n" +
            "    (SELECT b.lecturerId FROM ExamClassExaminerDetail b\n" +
            "    LEFT JOIN ExamClasses c\n" +
            "\t\tON b.examClassId = c.id\n" +
            "\tWHERE c.date = ?1 AND c.examShift = ?2) \n" +
            "    AND a.id <> ?3 AND a.role IN (0, 2)")
    Iterable<Lecturers> findExaminersIsFree(Date date, Integer examShift, Integer  lecturerId);
}
