package com.example.be.controller;

import com.example.be.exceptions.ResourceNotFoundException;
import com.example.be.model.Lecturers;
import com.example.be.request.LecturerRequest;
import com.example.be.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.*;

import static com.example.be.model.Lecturers.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin")
public class LecturerController {
    @Autowired
    private LecturerService lecturerService;

    private final List<Integer> LECTURER = Arrays.asList(Roles.LECTURER.ordinal(), Roles.ALL.ordinal());
    private final List<Integer> EXAMINER = Arrays.asList(Roles.EXAMINER.ordinal(), Roles.ALL.ordinal());

    @GetMapping("/lecturersAndExaminer")
    public ResponseEntity<Iterable<Lecturers>> getAllLecturersAndExaminers() {
        return new ResponseEntity<>(lecturerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/lecturers")
    public ResponseEntity<List<Lecturers>> getAllLecturers() {
        return new ResponseEntity<>(
                lecturerService.findByRoleIn(LECTURER),
                HttpStatus.OK);
    }

    @GetMapping("/examiners")
    public ResponseEntity<List<Lecturers>> getAllExaminers() {
        return new ResponseEntity<>(
                lecturerService.findByRoleIn(EXAMINER),
                HttpStatus.OK);
    }

    @GetMapping("/lecturers/{id}")
    public ResponseEntity<Lecturers> getLecturer(@PathVariable Long id) {
        Optional<Lecturers> moduleOptional = lecturerService.findByIdAndRoleIn(id, LECTURER);
        return moduleOptional.map(lecturer -> new ResponseEntity<>(lecturer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping("/examiners/{id}")
    public ResponseEntity<Lecturers> getExaminer(@PathVariable Long id) {
        Optional<Lecturers> moduleOptional = lecturerService.findByIdAndRoleIn(id, EXAMINER);
        return moduleOptional.map(lecturer -> new ResponseEntity<>(lecturer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PostMapping(value = {"/lecturers", "/examiners"})
    public ResponseEntity<Lecturers> createNewLecturer(@Valid @RequestBody LecturerRequest lecturer) {
        try {
            Lecturers newLecturer = new Lecturers(
                    lecturer.getName(),
                    lecturer.getPhone(),
                    lecturer.getWorkRoom(),
                    lecturer.getUserId(),
                    lecturer.getRole()
            );
            return new ResponseEntity<>(lecturerService.update(newLecturer), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = {"/lecturers/{id}", "/examiners/{id}"})
    public ResponseEntity<Lecturers> updateLecturer(@PathVariable Long id, @Valid @RequestBody LecturerRequest lecturers) {
        Optional<Lecturers> lecturerOptional = lecturerService.findById(id);

        try {
            Lecturers updateLecturer = lecturerOptional.get();
            updateLecturer.setName(lecturers.getName());
            updateLecturer.setPhone(lecturers.getPhone());
            updateLecturer.setWorkRoom(lecturers.getWorkRoom());
            updateLecturer.setRole(lecturers.getRole());
            return new ResponseEntity<>(lecturerService.update(updateLecturer), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/lecturers/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Lecturers> patchLecturer(@PathVariable Long id, @RequestBody Map<Object, Object> fields) {
        Optional<Lecturers> lecturer = lecturerService.findById(id);

        if (lecturer.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Lecturers.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, lecturer.get(), value);
            });
            Lecturers updateLecturer = lecturerService.update(lecturer.get());
            return new ResponseEntity<>(updateLecturer, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
