package exam.management.be.controller.admin;

import exam.management.be.exceptions.ResourceNotFoundException;
import exam.management.be.model.Lecturers;
import exam.management.be.request.CreateExaminerRequest;
import exam.management.be.request.CreateLectureRequest;
import exam.management.be.request.LecturerRequest;
import exam.management.be.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

import static exam.management.be.model.Lecturers.*;

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
    public ResponseEntity<?> getAllLecturers() {
        return new ResponseEntity<>(
                lecturerService.getAllLecturersOrExaminers(LECTURER),
                HttpStatus.OK);
    }

    @GetMapping("/examiners")
    public ResponseEntity<?> getAllExaminers() {
        return new ResponseEntity<>(
                lecturerService.getAllLecturersOrExaminers(EXAMINER),
                HttpStatus.OK);
    }

    @GetMapping("/lecturers/{id}")
    public ResponseEntity<Lecturers> getLecturer(@PathVariable Integer id) {
        Optional<Lecturers> lecturerOptional = lecturerService.findByIdAndRoleIn(id, LECTURER);
        return lecturerOptional.map(lecturer -> new ResponseEntity<>(lecturer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping("/examiners/{id}")
    public ResponseEntity<Lecturers> getExaminer(@PathVariable Integer id) {
        Optional<Lecturers> lecturerOptional = lecturerService.findByIdAndRoleIn(id, EXAMINER);
        return lecturerOptional.map(lecturer -> new ResponseEntity<>(lecturer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PostMapping("/lecturers")
    public ResponseEntity<Lecturers> createNewLecturer(@RequestBody @Valid CreateLectureRequest lecturer) {
        try {
            return lecturerService.createLecturers(lecturer);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/examiners")
    public ResponseEntity<Lecturers> createNewExaminer(@RequestBody @Valid CreateExaminerRequest examiner) {
        try {
            return lecturerService.createExaminers(examiner);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = {"/lecturers/{id}", "/examiners/{id}"})
    public ResponseEntity<Lecturers> updateLecturer(@PathVariable Integer id, @Valid @RequestBody LecturerRequest lecturers) {
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

    @PatchMapping(value = {"/examiners/{id}", "/lecturers/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patchLecturer(@PathVariable Integer id, @RequestBody Map<Object, Object> fields) {
        try {
            return new ResponseEntity<>(lecturerService.update(id, fields), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = {"/lecturers/{id}", "/examiners/{id}"})
    public ResponseEntity<Lecturers> deleteLecturer(@PathVariable Integer id) {
        Optional<Lecturers> lecturerOptional = lecturerService.findById(id);
        return lecturerOptional.map(lecturer -> {
            lecturerService.remove(id);
            return new ResponseEntity<>(lecturer, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping(value = "/examiners/free", params = {"date", "examShift", "examClassId"})
    public ResponseEntity<?> listOfExaminersIsFree(
            @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
            @RequestParam Integer examShift,
            @RequestParam Integer examClassId
    ) {
        return lecturerService.listOfExaminersIsFree(date, examShift, examClassId);
    }

    @GetMapping(value = "/examiners", params = {"examClassId"})
    public ResponseEntity<?> getExaminersByExamClassId(@RequestParam Integer examClassId) {
        return new ResponseEntity<>(lecturerService.getExaminersByExamClassId(examClassId), HttpStatus.OK);
    }
}
