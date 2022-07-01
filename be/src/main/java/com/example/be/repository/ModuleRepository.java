package com.example.be.repository;

import com.example.be.model.Modules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Modules, Long> {
    @Query(value = "SELECT m.id, m.name, m.code FROM modules m", nativeQuery = true)
    List<Object[]> listModules();
}
