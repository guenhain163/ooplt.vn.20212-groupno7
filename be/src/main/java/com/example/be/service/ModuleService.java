package com.example.be.service;

import com.example.be.model.Modules;
import com.example.be.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ModuleService implements BaseService<Modules> {
    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public Iterable<Modules> findAll() {
        return moduleRepository.findAll();
    }

    @Override
    public Optional<Modules> findById(Integer id) {
        return moduleRepository.findById(id);
    }

    @Override
    public Modules save(Modules lecture) {
        return moduleRepository.save(lecture);
    }

    @Override
    public Modules update(Modules modules) {
        return moduleRepository.saveAndFlush(modules);
    }

    @Override
    public void remove(Integer id) {
        moduleRepository.deleteById(id);
    }

    public List<Map<String, Object>> listModules() {
        return moduleRepository.listModules();
    }

    public List<String> listModuleNamesById(List<Integer> idList) {
        return moduleRepository.listModuleNamesById(idList);
    }

    public List<String> listModuleNamesByLecturerId(Integer lecturerId) {
        return moduleRepository.listModuleNamesByLecturerId(lecturerId);
    }
}
