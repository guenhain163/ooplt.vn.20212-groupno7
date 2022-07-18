package exam.management.be.service;

import exam.management.be.model.Classes;
import exam.management.be.model.ExamClasses;
import exam.management.be.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClassService implements BaseService<Classes> {
    @Autowired
    private ClassRepository classRepository;
    @Autowired @Lazy
    private ExamClassService examClassService;

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

    public Object findByRegisteredExam(Integer classId) {
        Classes classes = classRepository.findById(classId).get();
        classes.setRegisteredExam(new Date());
        classRepository.save(classes);
        return examClassService.save(
                new ExamClasses(classId, "20222", ExamClasses.Status.REGISTERED.ordinal()));
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

    public Optional<Classes> findByIdAndModuleId(Integer id, Integer moduleId) {
        return classRepository.findByIdAndModuleId(id, moduleId);
    }
}
