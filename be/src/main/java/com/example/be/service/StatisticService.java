package com.example.be.service;

import com.example.be.model.Classes;
import com.example.be.model.ExamClassDetail;
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

    public List<Map<String, Object>> getExamClassByLecturerId(Integer lecturerId) {
        Iterable<ExamClasses> classOptional = examClassService.findByLectureId(lecturerId);
        List<Map<String, Object>> list = new ArrayList<>();
        classOptional.forEach(el -> {
            Map<String, Object> map = new HashMap<>();
            ExamClassDetail examClassDetail = el.getExamClassDetailsById();
            map.put("status", el.getStatus());
            map.put("examCode", el.getExamCode());
            map.put("classCode", el.getClassesByClassId().getCode());
            map.put("moduleName", el.getClassesByClassId().getModulesByModuleId().getName());
            map.put("moduleCode", el.getClassesByClassId().getModulesByModuleId().getCode());
            map.put("cost", examClassDetail.getCost());
            map.put("numberStudent", examClassDetail.getNumberStudent());
            map.put("printingCost", examClassDetail.getPrintingCost());
            map.put("examinationCode", examClassDetail.getExaminationCost());
            list.add(map);
        });

        return list;
    }


    public ResponseEntity<?> getStatisticLecturer(Integer lecturerId) {
        Map<String, Object> listResult = new HashMap<>();
        listResult.put("lecturer", getLecturerByRole(lecturerId));
        listResult.put("classes", getExamClassByLecturerId(lecturerId));


        return new ResponseEntity<>(listResult, HttpStatus.OK);
    }
}
