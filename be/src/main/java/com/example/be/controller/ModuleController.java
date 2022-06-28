package com.example.be.controller;

import com.example.be.model.Modules;
import com.example.be.service.BaseService;
import com.example.be.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/modules")
public class ModuleController {
    @Autowired
    private ModuleService moduleService;

    @GetMapping
    public ResponseEntity<Iterable<Modules>> getAllModules() {
        return new ResponseEntity<>(moduleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modules> getModule(@PathVariable Long id) {
        Optional<Modules> categoryOptional = moduleService.findById(id);
        return categoryOptional.map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Modules> createNewModule(@RequestBody Modules category) {
        return new ResponseEntity<>(moduleService.save(category), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modules> updateModule(@PathVariable Long id, @RequestBody Modules modules) {
        Optional<Modules> moduleOptional = moduleService.findById(id);
        return moduleOptional.map(module -> {
            modules.setId(module.getId());
            return new ResponseEntity<>(moduleService.save(modules), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Modules> deleteModule(@PathVariable Long id) {
        Optional<Modules> moduleOptional = moduleService.findById(id);
        return moduleOptional.map(module -> {
            moduleService.remove(id);
            return new ResponseEntity<>(module, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
