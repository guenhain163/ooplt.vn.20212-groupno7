package exam.management.be.controller.user;

import exam.management.be.service.ExamClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static exam.management.be.model.Lecturers.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class ExamClassUserController {
    @Autowired
    private ExamClassService examClassService;

    private final List<Integer> LECTURER = Arrays.asList(Roles.LECTURER.ordinal(), Roles.ALL.ordinal());
    private final List<Integer> EXAMINER = Arrays.asList(Roles.EXAMINER.ordinal(), Roles.ALL.ordinal());

    @GetMapping(value = "/{id}/examClass", params = {"semester", "role"})
    public ResponseEntity<?> getAllExamClassesOfLecturer(@PathVariable Integer id, @RequestParam String semester,
                                                         @RequestParam String role) {
        if (Objects.equals(role, "lecturer"))
            return new ResponseEntity<>(examClassService.getAllByUserIdAndSemesterAndRole(id, semester, LECTURER), HttpStatus.OK);
        else
            return new ResponseEntity<>(examClassService.getAllByUserIdAndSemesterAndRole(id, semester, EXAMINER), HttpStatus.OK);
    }
}
