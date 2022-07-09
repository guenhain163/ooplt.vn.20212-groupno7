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
    @Autowired
    private ModuleService moduleService;

    private final List<Integer> LECTURER = Arrays.asList(Roles.LECTURER.ordinal(), Lecturers.Roles.ALL.ordinal());
    private final List<Integer> EXAMINER = Arrays.asList(Roles.EXAMINER.ordinal(), Lecturers.Roles.ALL.ordinal());


    public Map<String, Object> getLecturerByRole(Integer lecturerId) {
        Optional<Lecturers> lecturerOptional = lecturerService.findByIdAndRoleIn(lecturerId, LECTURER);
        Lecturers lecturer = lecturerOptional.get();
        Map<String, Object> map = new HashMap<>();
        map.put("id", lecturer.getId());
        map.put("name", lecturer.getName());
        map.put("phone", lecturer.getPhone());
        map.put("workRoom", lecturer.getWorkRoom());
        map.put("role", lecturer.getRole());
        map.put("email", lecturer.getUsers().getEmail());
        map.put("modules", moduleService.listModuleNamesByLecturerId(lecturerId));
        return map;
    }

    public ResponseEntity<?> getStatisticLecturer(Integer lecturerId) {
        Map<String, Object> listResult = new HashMap<>();
        listResult.put("classes", getLecturerByRole(lecturerId));



        return new ResponseEntity<>(listResult, HttpStatus.OK);
    }
}
