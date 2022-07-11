package exam.management.be.controller.user;

import exam.management.be.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user/classes")
public class ClassUserController {
    @Autowired
    private ClassService classService;

    @GetMapping(value = "/{lecturerId}", params = {"semester"})
    public ResponseEntity<?> getAllClasses(@PathVariable Integer lecturerId, @RequestParam String semester) {
        return new ResponseEntity<>(classService.findAllByLecturerIdAndSemester(lecturerId, semester), HttpStatus.OK);
    }
}
