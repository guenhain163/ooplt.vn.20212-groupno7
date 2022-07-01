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
    public Optional<Modules> findById(Long id) {
        return moduleRepository.findById(id);
    }

    @Override
    public Modules save(Modules lecture) {
        return moduleRepository.save(lecture);
    }

    @Override
    public void remove(Long id) {
        moduleRepository.deleteById(id);
    }

    public List<Map<String, Object>> listModules() {
        List<Object[]> modules = moduleRepository.listModules();

        List<Map<String, Object>> listModules = new ArrayList<Map<String, Object>>();

        for (Object[] module : modules) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", module[2]);
            map.put("name", module[1]);
            map.put("id", module[0]);
            listModules.add(map);
        }

        return new ArrayList<>(listModules);
    }

}
