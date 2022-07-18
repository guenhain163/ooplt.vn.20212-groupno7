package exam.management.be.controller.user;

import exam.management.be.model.Classes;
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

    @GetMapping(value = "/{classId}", params = {"registeredExam"})
    public ResponseEntity<?> getRegisteredClasses(@RequestParam boolean registeredExam,
                                                                  @PathVariable Integer classId) {
        if (registeredExam) {
            return new ResponseEntity<>(classService.findByRegisteredExam(classId), HttpStatus.OK);
        }

        return new ResponseEntity<>("Đăng ký thi không thành công", HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
