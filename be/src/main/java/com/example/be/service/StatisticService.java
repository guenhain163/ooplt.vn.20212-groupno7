package com.example.be.service;

import com.example.be.model.Classes;
import com.example.be.model.ExamClasses;
import com.example.be.model.Lecturers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.be.model.Lecturers.*;

@Service
public class StatisticService {
    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private ClassService classService;
    @Autowired
    private ExamClassService examClassService;

    private final List<Integer> LECTURER = Arrays.asList(Roles.LECTURER.ordinal(), Lecturers.Roles.ALL.ordinal());
    private final List<Integer> EXAMINER = Arrays.asList(Roles.EXAMINER.ordinal(), Lecturers.Roles.ALL.ordinal());

    public ResponseEntity<?> getStatisticLecturer(Integer lecturerId) {
        List<Map<String, Object>> listResult = new ArrayList<>();

        Optional<Lecturers> lecturerOptional = lecturerService.findByIdAndRoleIn(lecturerId, LECTURER);

        Iterable<Classes> classList = classService.findByLectureId(lecturerId);



        if (lecturerOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Map<String, Object> lecturer = new HashMap<>();
        lecturer.put("lecturer", lecturerOptional.get());
        listResult.add(lecturer);

        Map<String, Object> classes = new HashMap<>();
        classes.put("classes", classes);



        return new ResponseEntity<>(listResult, HttpStatus.OK);
    }
}
