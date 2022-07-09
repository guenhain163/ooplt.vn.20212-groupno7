package com.example.be.controller;

import com.example.be.exceptions.ResourceNotFoundException;
import com.example.be.model.ExamClassExaminerDetail;
import com.example.be.model.ExamClasses;
import com.example.be.request.ExamClassRequest;
import com.example.be.service.ExamClassExaminerDetailService;
import com.example.be.service.ExamClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/examClasses")
public class ExamClassesController {
    @Autowired
    private ExamClassService examClassService;

    @GetMapping
    public ResponseEntity<Iterable<ExamClasses>> getAllExamClasses() {
        return new ResponseEntity<>(examClassService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamClasses> getExamClass(@PathVariable Integer id) {
        Optional<ExamClasses> examCLassOptional = examClassService.findById(id);
        return examCLassOptional.map(examClass -> new ResponseEntity<>(examClass, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PostMapping
    public ResponseEntity<ExamClasses> createExamClasses(@Valid @RequestBody ExamClassRequest examClass) {
        try {
            return new ResponseEntity<>(examClassService.create(examClass), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamClasses> updateExamClasses(@PathVariable Integer id,
                                                         @Valid @RequestBody ExamClassRequest examClass) {
        try {
            return new ResponseEntity<>(examClassService.update(id, examClass), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExamClasses> patchExamClass(@PathVariable Integer id, @RequestBody Map<Object, Object> fields) {
        try {
            return new ResponseEntity<>(examClassService.patch(id, fields), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExamClasses> deleteExamClass(@PathVariable Integer id) {
        Optional<ExamClasses> examClassesOptional = examClassService.findById(id);
        return examClassesOptional.map(examClass -> {
            examClassService.remove(id);
            return new ResponseEntity<>(examClass, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PostMapping("/{id}/examiners/division")
    public ResponseEntity<?> divisionExamClass(@PathVariable Integer id, @RequestBody List<Integer> examinersId) {
        try {
            return examClassService.division(id, examinersId);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
