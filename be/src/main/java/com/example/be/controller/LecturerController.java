package com.example.be.controller;

import com.example.be.model.Lecturers;
import com.example.be.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.example.be.model.Lecturers.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin")
public class LecturerController {
    @Autowired
    private LecturerService lecturerService;

    private final List<Integer> LECTURE = Arrays.asList(ROLE_LECTURER, ROLE_ALL);
    private final List<Integer> EXAMINE = Arrays.asList(ROLE_EXAMINER, ROLE_ALL);

    @GetMapping("/lecturersAndExaminer")
    public ResponseEntity<Iterable<Lecturers>> getAllLecturersAndExaminers() {
        return new ResponseEntity<>(lecturerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/lecturers")
    public ResponseEntity<List<Lecturers>> getAllLecturers() {
        return new ResponseEntity<>(
                lecturerService.findByRoleIn(LECTURE),
                HttpStatus.OK);
    }

    @GetMapping("/examiners")
    public ResponseEntity<List<Lecturers>> getAllExaminers() {
        return new ResponseEntity<>(
                lecturerService.findByRoleIn(EXAMINE),
                HttpStatus.OK);
    }

    @GetMapping("/lecturers/{id}")
    public ResponseEntity<Lecturers> getLecturer(@PathVariable Long id) {
        Optional<Lecturers> moduleOptional = lecturerService.findByIdAndRoleIn(id, LECTURE);
        return moduleOptional.map(lecturer -> new ResponseEntity<>(lecturer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping("/examiners/{id}")
    public ResponseEntity<Lecturers> getExaminer(@PathVariable Long id) {
        Optional<Lecturers> moduleOptional = lecturerService.findByIdAndRoleIn(id, EXAMINE);
        return moduleOptional.map(lecturer -> new ResponseEntity<>(lecturer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PostMapping(value = {"/lecturers", "/examiners"})
    public ResponseEntity<Lecturers> createNewLecturer(@RequestBody Lecturers lecturer) {
        return new ResponseEntity<>(lecturerService.save(lecturer), HttpStatus.OK);
    }

    @PutMapping(value = {"/lecturers/{id}", "/examiners/{id}"})
    public ResponseEntity<Lecturers> updateLecturer(@PathVariable Long id, @RequestBody Lecturers lecturers) {
        Optional<Lecturers> lecturerOptional = lecturerService.findById(id);
        return lecturerOptional.map(lecturer -> {
            lecturers.setId(lecturer.getId());
            return new ResponseEntity<>(lecturerService.save(lecturers), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping(value = {"/lecturers/{id}", "/examiners/{id}"})
    public ResponseEntity<Lecturers> deleteModule(@PathVariable Long id) {
        Optional<Lecturers> lecturerOptional = lecturerService.findById(id);
        return lecturerOptional.map(lecturer -> {
            lecturerService.remove(id);
            return new ResponseEntity<>(lecturer, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
