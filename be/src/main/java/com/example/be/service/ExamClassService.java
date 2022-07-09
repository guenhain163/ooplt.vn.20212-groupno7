package com.example.be.service;

import com.example.be.model.ExamClassExaminerDetail;
import com.example.be.model.ExamClassLecturerDetail;
import com.example.be.model.ExamClasses;
import com.example.be.repository.ExamClassLecturerDetailRepository;
import com.example.be.repository.ExamClassRepository;
import com.example.be.request.ExamClassRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.be.model.ExamClasses.Status.REGISTERED;

@Service
public class ExamClassService implements BaseService<ExamClasses> {
    @Autowired
    private ExamClassRepository examClassRepository;
    @Autowired
    private ExamClassExaminerDetailService examClassExaminerDetailService;
    @Autowired
    private ExamClassLecturerDetailService examClassLecturerDetailService;

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
        updateExamClass.setStatus(examClass.getStatus());
        if (!examClass.getNote().isEmpty()) {
            updateExamClass.setNote(examClass.getNote());
        }

//        if (examClass.getStatus()) {
//            updateExamClass.setStatus(examClass.getStatus());
//        }

        return this.update(updateExamClass);
    }

    public ExamClasses patch(Integer id, Map<Object, Object> fields) {
        Optional<ExamClasses> examClass = this.findById(id);

        if (examClass.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(ExamClasses.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, examClass.get(), value);
            });
            return this.update(examClass.get());
        } else return null;
    }

    public ResponseEntity<?> division(Integer id, List<Integer> examinersId) {
        Optional<ExamClasses> examClassesOptional = this.findByIdAndStatus(id, REGISTERED.ordinal());
        Optional<ExamClassLecturerDetail> examClassLecturerDetail = examClassLecturerDetailService.findByExamClassId(id);

        if (examClassesOptional.isEmpty() || examClassLecturerDetail.isEmpty()) {
            return new ResponseEntity<>("Exam class doesn't exist.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        int numberStudent = examClassLecturerDetail.get().getNumberStudent();
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
