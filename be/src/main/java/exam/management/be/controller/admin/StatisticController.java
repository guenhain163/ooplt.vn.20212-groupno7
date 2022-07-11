package exam.management.be.controller.admin;

import exam.management.be.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/statistic")
public class StatisticController {
    @Autowired
    private StatisticService statisticService;

    @GetMapping("/lecturers/{id}/{semester}")
    public ResponseEntity<?> getStatisticLecturer(@PathVariable Integer id, @PathVariable String semester) {
        return statisticService.getStatisticLecturer(id, semester);
    }

    @GetMapping("/lecturers")
    public ResponseEntity<?> getLecturers() {
        return new ResponseEntity<>(statisticService.getLecturers(), HttpStatus.OK);
    }

    @GetMapping("/examiners")
    public ResponseEntity<?> getExaminers() {
        return new ResponseEntity<>(statisticService.getExaminers(), HttpStatus.OK);
    }

    @GetMapping("/examiners/{id}/{semester}")
    public ResponseEntity<?> getStatisticExaminer(@PathVariable Integer id, @PathVariable String semester) {
        return statisticService.getStatisticExaminer(id, semester);
    }

    @GetMapping("/classes/{semester}")
    public ResponseEntity<?> getStatisticClass(@PathVariable String semester) {
        return statisticService.getStatisticClass(semester);
    }
}
