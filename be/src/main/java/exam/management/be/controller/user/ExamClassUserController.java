package exam.management.be.controller.user;

import exam.management.be.service.ExamClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user/examClass")
public class ExamClassUserController {
    @Autowired
    private ExamClassService examClassService;

    @GetMapping(value = "/{lecturerId}", params = {"semester"})
    public ResponseEntity<?> getAllClasses(@PathVariable Integer lecturerId, @RequestParam String semester) {
        return new ResponseEntity<>(examClassService.getAllByLecturerIdAndSemester(lecturerId, semester), HttpStatus.OK);
    }
}
