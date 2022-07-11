package exam.management.be.service;

import exam.management.be.model.ExamClassExaminerDetail;
import exam.management.be.model.ExamClassDetail;
import exam.management.be.model.ExamClasses;
import exam.management.be.repository.ExamClassRepository;
import exam.management.be.request.ExamClassRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

import static exam.management.be.model.ExamClasses.Status.REGISTERED;

@Service
public class ExamClassService implements BaseService<ExamClasses> {
    @Autowired
    private ExamClassRepository examClassRepository;
    @Autowired
    private ExamClassExaminerDetailService examClassExaminerDetailService;
    @Autowired
    private ExamClassDetailService examClassDetailService;

    @Override
    public Iterable<ExamClasses> findAll() {
        return examClassRepository.findAll();
    }

    @Override
    public Optional<ExamClasses> findById(Integer id) {
        return examClassRepository.findById(id);
    }

    @Override
    public ExamClasses save(ExamClasses examClass) {
        return examClassRepository.save(examClass);
    }

    @Override
    public ExamClasses update(ExamClasses examClass) {
        return examClassRepository.saveAndFlush(examClass);
    }

    @Override
    public void remove(Integer id) {
        examClassRepository.deleteById(id);
    }

    public Optional<ExamClasses> findByIdAndStatus(Integer id, Integer status) {
        return examClassRepository.findByIdAndStatus(id, status);
    }

    public Iterable<ExamClasses> findByLectureId(Integer lectureId) {
        return examClassRepository.findByLectureId(lectureId);
    }

    public Iterable<ExamClasses> findByLectureIdAndSemester(Integer lectureId, String semester) {
        return examClassRepository.findByLectureIdAndSemester(lectureId, semester);
    }

    public Object getAllExamClasses() {
        return examClassRepository.getAllExamClasses();
    }

    public ExamClasses create(@NotNull ExamClassRequest examClass) {
        ExamClasses newExamClass = new ExamClasses(
                examClass.getClassId(),
                examClass.getExamShift(),
                examClass.getDate(),
                examClass.getWeek(),
                examClass.getOpeningPeriod(),
                examClass.getRoom()
        );
        if (!examClass.getNote().isEmpty()) {
            newExamClass.setNote(examClass.getNote());
        }

        return this.save(newExamClass);
    }

    public ExamClasses update(Integer id, @NotNull ExamClassRequest examClass) {
        Optional<ExamClasses> examClassesOptional = this.findById(id);
        ExamClasses updateExamClass = examClassesOptional.get();
        updateExamClass.setClassId(examClass.getClassId());
        updateExamClass.setExamShift(examClass.getExamShift());
        updateExamClass.setDate(examClass.getDate());
        updateExamClass.setWeek(examClass.getWeek());
        updateExamClass.setOpeningPeriod(examClass.getOpeningPeriod());
        updateExamClass.setRoom(examClass.getRoom());
        if (!examClass.getNote().isEmpty()) {
            updateExamClass.setNote(examClass.getNote());
        }

        if (examClass.getStatus() <= ExamClasses.Status.CLOSED.ordinal()
                || examClass.getStatus() >= ExamClasses.Status.NEW.ordinal()) {
            updateExamClass.setStatus(examClass.getStatus());
        }

        return this.update(updateExamClass);
    }

    public ExamClasses patch(Integer id, Map<String, Object> fields) {
        ExamClasses examClass = examClassRepository.findById(id).get();

        fields.forEach((key, value) -> {
            if (!Objects.equals(key, "numberStudent")) {
                Field field = ReflectionUtils.findField(ExamClasses.class, key);
                assert field != null;
                field.setAccessible(true);
                ReflectionUtils.setField(field, examClass, value);
            } else {
                ExamClassDetail examClassDetail = examClassDetailService.findByExamClassId(id).get();
                examClassDetail.setNumberStudent((Integer) value);
                examClassDetailService.update(examClassDetail);
            }
        });

        System.out.println(examClass.getClassId());

        return examClassRepository.saveAndFlush(examClass);
    }

    public ResponseEntity<?> division(Integer id, List<Integer> examinersId) {
        Optional<ExamClasses> examClassesOptional = this.findByIdAndStatus(id, REGISTERED.ordinal());
        Optional<ExamClassDetail> examClassDetail = examClassDetailService.findByExamClassId(id);

        if (examClassesOptional.isEmpty() || examClassDetail.isEmpty()) {
            return new ResponseEntity<>("Exam class doesn't exist.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        int numberStudent = examClassDetail.get().getNumberStudent();
        if (numberStudent >= 60 && examinersId.size() != 2 || numberStudent < 60 && examinersId.size() != 1) {
            return new ResponseEntity<>("Invalid number of examiners.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        List<ExamClassExaminerDetail> division = new ArrayList<>();
        examinersId.forEach((value) -> {
            ExamClassExaminerDetail examClassExaminerDetail =
                    new ExamClassExaminerDetail(id, value);
            division.add(examClassExaminerDetail);
        });
        examClassExaminerDetailService.save(division);
        return new ResponseEntity<>(examClassesOptional.get(), HttpStatus.OK);
    }
}
