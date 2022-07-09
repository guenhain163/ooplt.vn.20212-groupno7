package com.example.be.service;

import com.example.be.model.*;
import com.example.be.repository.ExamClassLecturerDetailRepository;
import com.example.be.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.be.model.Lecturers.*;

@Service
public class LecturerService implements BaseService<Lecturers> {
    @Autowired
    private LecturerRepository lecturerRepository;
    @Autowired
    private ExamClassLecturerDetailRepository examClassLecturerDetailRepository;
    @Autowired
    private ModuleService moduleService;


    private final List<Integer> LECTURER = Arrays.asList(Lecturers.Roles.LECTURER.ordinal(), Lecturers.Roles.ALL.ordinal());

    @Override
    public Iterable<Lecturers> findAll() {
        return lecturerRepository.findAll();
    }

    @Override
    public Optional<Lecturers> findById(Integer id) {
        return lecturerRepository.findById(id);
    }

    @Override
    public Lecturers save(Lecturers modules) {
        return lecturerRepository.save(modules);
    }

    @Override
    public Lecturers update(Lecturers lecturer) {
        return lecturerRepository.saveAndFlush(lecturer);
    }

    @Override
    public void remove(Integer id) {
        lecturerRepository.deleteById(id);
    }

    public List<Lecturers> findByRoleIn(List<Integer> roles) {
        return lecturerRepository.findByRoleIn(roles);
    }

    public Optional<Lecturers> findByIdAndRoleIn(Integer id, List<Integer> roles) {
        return lecturerRepository.findByIdAndRoleIn(id, roles);
    }

    public Iterable<Lecturers> listOfExaminersIsFree(Date date, Integer examShift, Integer examClassId) {
        Optional<ExamClassLecturerDetail> examClassLecturerDetail =
                examClassLecturerDetailRepository.findByExamClassId(examClassId);

        if (examClassLecturerDetail.isPresent()) {
            Integer lecturerId = examClassLecturerDetail.get().getLectureId();
            return lecturerRepository.findExaminersIsFree(date, examShift, lecturerId);
        } else return null;
    }

    public List<Map<String, Object>> getAllLecturers() {
        List<Lecturers> lecturerList = this.findByRoleIn(LECTURER);
        System.out.println(lecturerList);

        List<Map<String, Object>> resultsList = new ArrayList<Map<String, Object>>();
        for (Lecturers lecturer : lecturerList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", lecturer.getName());
            map.put("phone", lecturer.getPhone());
            map.put("room", lecturer.getWorkRoom());
            Users user = lecturer.getUsers();
            map.put("email", user.getEmail());
            List<Integer> moduleIdList = lecturer.getSpecialitiesById().stream().map(Speciality::getModuleId).toList();
            map.put("modules", moduleService.listModuleNamesById(moduleIdList));

            resultsList.add(map);
        }

        return resultsList;
    }
}
