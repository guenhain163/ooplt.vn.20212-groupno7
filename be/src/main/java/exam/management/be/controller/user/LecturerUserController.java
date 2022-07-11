package exam.management.be.controller.user;

import exam.management.be.exceptions.ResourceNotFoundException;
import exam.management.be.model.Lecturers;
import exam.management.be.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class LecturerUserController {
    @Autowired
    private LecturerService lecturerService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getLecturer(@PathVariable Integer id) {
        System.out.println(id);
        return new ResponseEntity<>(lecturerService.findByUserId(id), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Lecturers> patchLecturer(@PathVariable Integer id, @RequestBody Map<Object, Object> fields) {
        try {
            return new ResponseEntity<>(lecturerService.update(id, fields), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
