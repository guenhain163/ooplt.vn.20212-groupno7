package com.example.be.service;

import com.example.be.model.Modules;
import com.example.be.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleService implements BaseService<Modules> {
    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public Iterable<Modules> findAll() {
        return moduleRepository.findAll();
    }

    @Override
    public Optional<Modules> findById(Long id) {
        return moduleRepository.findById(id);
    }

    @Override
    public Modules save(Modules category) {
        return moduleRepository.save(category);
    }

    @Override
    public void remove(Long id) {
        moduleRepository.deleteById(id);
    }
}
