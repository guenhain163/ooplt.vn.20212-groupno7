package exam.management.be.controller.admin;

import exam.management.be.exceptions.ResourceNotFoundException;
import exam.management.be.model.ExamClasses;
import exam.management.be.request.ExamClassRequest;
import exam.management.be.request.ImportExamClassRequest;
import exam.management.be.service.ExamClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.HashMap;
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
    public ResponseEntity<?> getAllExamClasses() {
        return new ResponseEntity<>(examClassService.getAllExamClasses(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamClasses> getExamClass(@PathVariable Integer id) {
        Optional<ExamClasses> examCLassOptional = examClassService.findById(id);
        return examCLassOptional.map(examClass -> new ResponseEntity<>(examClass, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PostMapping
    public ResponseEntity<Object> createExamClasses(@Valid @RequestBody ExamClassRequest examClass) {
        try {
            return new ResponseEntity<>(examClassService.create(examClass), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{semester}/import")
    public ResponseEntity<Object> importExamClasses(@Valid @RequestBody List<ImportExamClassRequest> examClasses,
                                                    @PathVariable String semester) {
        try {
            return new ResponseEntity<>(examClassService.importExamClasses(examClasses, semester), HttpStatus.OK);
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
    public ResponseEntity<ExamClasses> patchExamClass(@PathVariable Integer id, @RequestBody Map<String, Object> fields) {
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

    @PostMapping("/{id}/status/paid")
    public ResponseEntity<?> paidExamClass(@PathVariable Integer id) {
        try {
            Map <String, Object> field = new HashMap<>();
            field.put("status", ExamClasses.Status.PAID.ordinal());
            return new ResponseEntity<>(examClassService.patch(id, field), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{semester}")
    public ResponseEntity<?> closeExamClass(@PathVariable String semester) {
        return new ResponseEntity<>(examClassService.closeExamClass(semester), HttpStatus.OK);
    }

    @GetMapping("/{id}/examiners/division")
    public ResponseEntity<?> getExaminersDivision(@PathVariable Integer id) {
        return new ResponseEntity<>(examClassService.getExaminersDivision(id), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/cost", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCosts(@PathVariable Integer id, @RequestBody Map<String, Object> fields) {
        try {
            return new ResponseEntity<>(examClassService.updateCosts(id, fields), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
