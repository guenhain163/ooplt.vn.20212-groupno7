package com.example.be.controller.admin;

import com.example.be.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/examiners/{id}/{semester}")
    public ResponseEntity<?> getStatisticExaminer(@PathVariable Integer id, @PathVariable String semester) {
        return statisticService.getStatisticExaminer(id, semester);
    }

    @GetMapping("/classes/{semester}")
    public ResponseEntity<?> getStatisticClass(@PathVariable String semester) {
        return statisticService.getStatisticClass(semester);
    }
}
