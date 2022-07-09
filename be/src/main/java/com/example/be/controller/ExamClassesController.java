package com.example.be.controller;

import com.example.be.exceptions.ResourceNotFoundException;
import com.example.be.model.ExamClasses;
import com.example.be.request.ExamClassRequest;
import com.example.be.service.ExamClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/examClasses")
public class ExamClassesController {
    @Autowired
    private ExamClassService examClassService;

    @GetMapping()
    public ResponseEntity<Iterable<ExamClasses>> getAllExamClasses() {
        return new ResponseEntity<>(examClassService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamClasses> getExamClass(@PathVariable Long id) {
        Optional<ExamClasses> examCLassOptional = examClassService.findById(id);
        return examCLassOptional.map(examClass -> new ResponseEntity<>(examClass, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PostMapping
    public ResponseEntity<ExamClasses> createExamClasses(@Valid @RequestBody ExamClassRequest examClass) {
        try {
            ExamClasses newExamClass = new ExamClasses(
                    examClass.getClassId(),
                    examClass.getExamShift(),
                    examClass.getDate(),
                    examClass.getWeek(),
                    examClass.getOpeningPeriod(),
                    examClass.getRoom()
            );

            if (!examClass.getNote().isEmpty()) {
                newExamClass.setNote(examClass.getNote());
            }

            return new ResponseEntity<>(examClassService.update(newExamClass), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamClasses> updateExamClasses(@PathVariable Long id,
                                                         @Valid @RequestBody ExamClassRequest examClass) {
        Optional<ExamClasses> examClassesOptional = examClassService.findById(id);

        try {
            ExamClasses updateExamClass = examClassesOptional.get();
            updateExamClass.setClassId(examClass.getClassId());
            updateExamClass.setExamShift(examClass.getExamShift());
            updateExamClass.setDate(examClass.getDate());
            updateExamClass.setWeek(examClass.getWeek());
            updateExamClass.setOpeningPeriod(examClass.getOpeningPeriod());
            updateExamClass.setRoom(examClass.getRoom());
            updateExamClass.setStatus(examClass.getStatus());

            if (!examClass.getNote().isEmpty()) {
                updateExamClass.setNote(examClass.getNote());
            }

//            if (examClass.getStatus()) {
//                updateExamClass.setStatus(examClass.getStatus());
//            }
            return new ResponseEntity<>(examClassService.update(updateExamClass), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExamClasses> patchExamClass(@PathVariable Long id, @RequestBody Map<Object, Object> fields) {
        Optional<ExamClasses> examClass = examClassService.findById(id);

        if (examClass.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(ExamClasses.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, examClass.get(), value);
            });
            ExamClasses updateExamClass = examClassService.update(examClass.get());
            return new ResponseEntity<>(updateExamClass, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExamClasses> deleteExamClass(@PathVariable Long id) {
        Optional<ExamClasses> examClassesOptional = examClassService.findById(id);
        return examClassesOptional.map(examClass -> {
            examClassService.remove(id);
            return new ResponseEntity<>(examClass, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

//    @PostMapping("/{id}")
//    public ResponseEntity<ExamClasses> divisionExamClass(@PathVariable Long id, ) {
//
//    }
}
