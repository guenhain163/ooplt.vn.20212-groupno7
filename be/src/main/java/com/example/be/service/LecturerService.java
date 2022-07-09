package com.example.be.service;

import com.example.be.model.*;
import com.example.be.repository.ExamClassLecturerDetailRepository;
import com.example.be.repository.LecturerRepository;
import com.example.be.request.CreateLectureRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LecturerService implements BaseService<Lecturers> {
    @Autowired
    private LecturerRepository lecturerRepository;
    @Autowired
    private ExamClassLecturerDetailRepository examClassLecturerDetailRepository;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private UserService userService;
    @Autowired
    private SpecialityService specialityService;

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

    public List<Map<String, Object>> getAllLecturersOrExaminers(List<Integer> roles) {
        List<Lecturers> lecturerList = this.findByRoleIn(roles);
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

    public Lecturers createLecturers(CreateLectureRequest lecturer) {
        Users user = userService.createDefault(lecturer.getEmail());

        Lecturers newLecturer = this.save(new Lecturers(
                lecturer.getName(),
                lecturer.getPhone(),
                lecturer.getWorkRoom(),
                user.getId(),
                Lecturers.Roles.LECTURER.ordinal()
        ));

        ArrayList<Speciality> specialities = new ArrayList<>();
        for (Integer moduleId : lecturer.getModules()) {
            Speciality speciality = new Speciality(newLecturer.getId(), moduleId);
            specialities.add(speciality);
        }

        specialityService.save(specialities);

        return newLecturer;
    }
}
