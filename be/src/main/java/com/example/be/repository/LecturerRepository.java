package com.example.be.repository;

import com.example.be.model.Lecturers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturers, Long> {
    List<Lecturers> findByRoleIn(List<Integer> roles);

    Optional<Lecturers> findByIdAndRoleIn(Long id, List<Integer> roles);
}
