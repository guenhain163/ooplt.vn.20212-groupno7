package com.example.be.service;

import com.example.be.model.*;
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
    @Autowired
    private ExamClassExaminerDetailService examClassExaminerDetailService;

    private final List<Integer> LECTURER = Arrays.asList(Roles.LECTURER.ordinal(), Lecturers.Roles.ALL.ordinal());
    private final List<Integer> EXAMINER = Arrays.asList(Roles.EXAMINER.ordinal(), Lecturers.Roles.ALL.ordinal());

    public Map<String, Object> getLecturerOrExaminer(Integer lecturerId, List<Integer> roles) {
        Optional<Lecturers> lecturerOptional = lecturerService.findByIdAndRoleIn(lecturerId, roles);
        if (lecturerOptional.isPresent()) {
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

        return null;
    }

    public List<Map<String, Object>> getExamClassByLecturerId(Integer lecturerId, String semester) {
        Iterable<ExamClasses> classOptional = examClassService.findByLectureIdAndSemester(lecturerId, semester);
        List<Map<String, Object>> list = new ArrayList<>();
        classOptional.forEach(el -> {
            Map<String, Object> map = new HashMap<>();
            ExamClassDetail examClassDetail = el.getExamClassDetailsById();
            if (examClassDetail != null) {
                map.put("status", el.getStatus());
                map.put("examCode", el.getExamCode());
                map.put("classCode", el.getClassesByClassId().getCode());
                map.put("moduleName", el.getClassesByClassId().getModulesByModuleId().getName());
                map.put("moduleCode", el.getClassesByClassId().getModulesByModuleId().getCode());
                map.put("date", el.getDate());
                map.put("note", el.getNote());
                map.put("cost", examClassDetail.getCost());
                map.put("numberStudent", examClassDetail.getNumberStudent());
                map.put("printingCost", examClassDetail.getPrintingCost());
                map.put("examinationCost", examClassDetail.getExaminationCost());
                list.add(map);
            }
        });

        return list;
    }

    public ResponseEntity<?> getStatisticLecturer(Integer lecturerId, String semester) {
        Map<String, Object> listResult = new HashMap<>();
        Map<String, Object> lecturer = getLecturerOrExaminer(lecturerId, LECTURER);
        if (lecturer != null && !lecturer.isEmpty()) {
            listResult.put("lecturer", lecturer);
            listResult.put("classes", getExamClassByLecturerId(lecturerId, semester));
        }

        return new ResponseEntity<>(listResult, HttpStatus.OK);
    }

    public List<Map<String, Object>> getExamClassByExaminerId(Integer examinerId, String semester) {
        Iterable<ExamClassExaminerDetail> classOptional = examClassExaminerDetailService
                .findByLectureIdAndSemester(examinerId, semester);
        List<Map<String, Object>> list = new ArrayList<>();
        classOptional.forEach(el -> {
            Map<String, Object> map = new HashMap<>();
            ExamClasses examClasses = el.getExamClassesByExamClassId();
            if (examClasses != null) {
                map.put("cost", el.getCost());
                map.put("status", examClasses.getStatus());
                map.put("examCode", examClasses.getExamCode());
                map.put("classCode", examClasses.getClassesByClassId().getCode());
                map.put("moduleName", examClasses.getClassesByClassId().getModulesByModuleId().getName());
                map.put("moduleCode", examClasses.getClassesByClassId().getModulesByModuleId().getCode());
                map.put("examShift", examClasses.getExamShift());
                map.put("date", examClasses.getDate());
                map.put("note", examClasses.getNote());
                list.add(map);
            }
        });

        return list;
    }

    public ResponseEntity<?> getStatisticExaminer(Integer lecturerId, String semester) {
        Map<String, Object> listResult = new HashMap<>();
        Map<String, Object> lecturer = getLecturerOrExaminer(lecturerId, EXAMINER);
        if (lecturer != null && !lecturer.isEmpty()) {
            listResult.put("lecturer", lecturer);
            listResult.put("classes", getExamClassByExaminerId(lecturerId, semester));
        }

        return new ResponseEntity<>(listResult, HttpStatus.OK);
    }

    public ResponseEntity<?> getStatisticClass(String semester) {
        Iterable<Classes> classes = classService.findAllBySemester(semester);

        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    public List<Map<String, Object>> getLecturers() {
        return lecturerService.getLecturersOrExaminersBySemester(LECTURER);
    }

    public List<Map<String, Object>> getExaminers() {
        return lecturerService.getLecturersOrExaminersBySemester(EXAMINER);
    }
}
