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

    @GetMapping("/lecturer/{id}")
    public ResponseEntity<?> getStatisticLecturer(@PathVariable Integer lecturerId) {
        return statisticService.getStatisticLecturer(lecturerId);
    }
}
