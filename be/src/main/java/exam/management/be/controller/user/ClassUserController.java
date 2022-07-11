package exam.management.be.controller.user;

import exam.management.be.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class ClassUserController {
    @Autowired
    private ClassService classService;

    @GetMapping(value = "/{id}/classes", params = {"semester"})
    public ResponseEntity<?> getAllClasses(@PathVariable Integer id, @RequestParam String semester) {
        return new ResponseEntity<>(classService.findAllByUserIdAndSemester(id, semester), HttpStatus.OK);
    }
}
