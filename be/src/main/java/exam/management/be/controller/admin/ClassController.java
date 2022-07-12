package exam.management.be.controller.admin;

import exam.management.be.model.Classes;
import exam.management.be.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/classes")
public class ClassController {
    @Autowired
    private ClassService classService;

    @GetMapping
    public ResponseEntity<Iterable<Classes>> getAllClasses() {
        return new ResponseEntity<>(classService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classes> getClass(@PathVariable Integer id) {
        Optional<Classes> classOptional = classService.findById(id);
        return classOptional.map(class1 -> new ResponseEntity<>(class1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PutMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Classes> updateLecturer(@PathVariable Integer id, @RequestBody Classes classes) {
        Optional<Classes> classOptional = classService.findById(id);
        return classOptional.map(el -> {
            classes.setId(el.getId());
            return new ResponseEntity<>(classService.save(classes), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateRegisteredClass(
            @RequestBody Date date,
            @PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(classService.updateRegisteredClass(date, id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
