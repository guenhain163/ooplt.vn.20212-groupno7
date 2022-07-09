package com.example.be.service;

import com.example.be.model.ExamClassLecturerDetail;
import com.example.be.model.Lecturers;
import com.example.be.repository.ExamClassLecturerDetailRepository;
import com.example.be.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LecturerService implements BaseService<Lecturers> {
    @Autowired
    private LecturerRepository lecturerRepository;
    @Autowired
    private ExamClassLecturerDetailRepository examClassLecturerDetailRepository;

    @Override
    public Iterable<Lecturers> findAll() {
        return lecturerRepository.findAll();
    }

    @Override
    public Optional<Lecturers> findById(Long id) {
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
    public void remove(Long id) {
        lecturerRepository.deleteById(id);
    }

    public List<Lecturers> findByRoleIn(List<Integer> roles) {
        return lecturerRepository.findByRoleIn(roles);
    }

    public Optional<Lecturers> findByIdAndRoleIn(Long id, List<Integer> roles) {
        return lecturerRepository.findByIdAndRoleIn(id, roles);
    }

    public Iterable<Lecturers> listOfExaminersIsFree(Date date, Integer examShift, Long examClassId) {
        Optional<ExamClassLecturerDetail> examClassLecturerDetail =
                examClassLecturerDetailRepository.findByExamClassId(examClassId);
        System.out.println(date);
        System.out.println(examShift);
        System.out.println(examClassId);

        if (examClassLecturerDetail.isPresent()) {
            Long lecturerId = examClassLecturerDetail.get().getLectureId();
            return lecturerRepository.findExaminersIsFree(date, examShift, lecturerId);
        } else return null;
    }
}
