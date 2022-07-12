package exam.management.be.service;

import exam.management.be.model.Classes;
import exam.management.be.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClassService implements BaseService<Classes> {
    @Autowired
    private ClassRepository classRepository;

    @Override
    public Iterable<Classes> findAll() {
        return classRepository.findAll();
    }

    @Override
    public Optional<Classes> findById(Integer id) {
        return classRepository.findById(id);
    }

    @Override
    public Classes save(Classes classes) {
        return classRepository.save(classes);
    }

    @Override
    public Classes update(Classes classes) {
        return classRepository.saveAndFlush(classes);
    }

    public Classes updateRegisteredClass(Date date, Integer id) {
        return classRepository.updateRegisteredClass(date, id);
    }

    @Override
    public void remove(Integer id) {
        classRepository.deleteById(id);
    }

    public Iterable<Classes> findByRegisteredExam(boolean registeredExam) {
        if (registeredExam) {
            return classRepository.findByRegisteredExamIsNotNull();
        }
        return classRepository.findByRegisteredExamIsNull();
    }

    public Iterable<Classes> findByLectureId(Integer lectureId) {
        return classRepository.findByLecturerId(lectureId);
    }

    public Optional<Classes> findByExamClassId(Integer examClassId) {
        return classRepository.findByExamClassId(examClassId);
    }

    public List<Map<String, Object>> findAllBySemester(String semester) {
        return classRepository.findBySemester(semester);
    }

    public List<Map<String, Object>> findAllByUserIdAndSemester(Integer id, String semester) {
        return classRepository.findByUserIdAndSemester(id, semester);
    }
}