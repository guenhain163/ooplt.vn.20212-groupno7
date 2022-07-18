package exam.management.be.service;

import exam.management.be.model.*;
import exam.management.be.repository.ExamClassRepository;
import exam.management.be.request.ExamClassRequest;
import exam.management.be.request.ImportExamClassRequest;
import net.bytebuddy.utility.nullability.MaybeNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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
    @Autowired
    private ClassService classService;
    @Autowired
    private ModuleService moduleService;

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

    public Object getAllByUserIdAndSemesterAndRole(Integer lecturerId, String semester, List<Integer> roles) {
        return examClassRepository.findAllByUserIdAndSemesterAndRole(lecturerId, semester, roles);
    }

    public Object create(@NotNull ExamClassRequest examClass) {
        if (classService.findByIdAndModuleId(examClass.getClassId(), examClass.getModuleId()).isEmpty()) {
            return new ResponseEntity<>("Lớp học không hợp lệ.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (examClassRepository.findByClassId(examClass.getClassId()).isPresent()) {
            return new ResponseEntity<>("Lớp thi đã tồn tại.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        String maxExamCode = examClassRepository.findTopByOrderByExamCodeAsc().get().getExamCode();
        int examCode = Integer.parseInt(maxExamCode) + 1;

        ExamClasses newExamClass = new ExamClasses(
                examClass.getClassId(),
                examClass.getExamShift(),
                examClass.getDate(),
                examClass.getWeek(),
                examClass.getOpeningPeriod(),
                examClass.getRoom(),
                examClass.getSemester(),
                Integer.toString(examCode),
                examClass.getStatus(),
                examClass.getGroup()
        );

        if (!examClass.getNote().isEmpty()) {
            newExamClass.setNote(examClass.getNote());
        }

        newExamClass = this.save(newExamClass);

        examClassDetailService.save(new ExamClassDetail(newExamClass.getClassId(), examClass.getNumberStudent()));

        return newExamClass;
    }

    public Object importExamClasses(@NotNull List<ImportExamClassRequest> examClasses, String semester) {
        List listError = new ArrayList();

        examClasses.forEach((examClass) -> {
            Optional<Modules> moduleOptional = moduleService.findByCodeAndName(examClass.getCodeModule(), examClass.getNameModule());
            if (moduleOptional.isEmpty()) {
                listError.add("Mã học phần: " + examClass.getCodeModule() +
                        " và tên học phần: " + examClass.getNameModule() + " không khớp.");
                return;
            }

            Optional<Classes> classOptional = classService.findByCode(examClass.getCode());
            if (classOptional.isPresent()) {
                listError.add("Mã lớp: " + examClass.getCode() + " không tồn tại.");
                return;
            }

            String maxExamCode = examClassRepository.findTopByOrderByExamCodeAsc().get().getExamCode();
            int examCode = Integer.parseInt(maxExamCode) + 1;

            ExamClasses newExamClass = new ExamClasses(
                    classOptional.get().getId(),
                    examClass.getExamShift(),
                    examClass.getDate(),
                    examClass.getWeek(),
                    examClass.getOpeningPeriod(),
                    examClass.getRoom(),
                    semester,
                    Integer.toString(examCode),
                    examClass.getStatus(),
                    examClass.getGroup()
            );

            if (!examClass.getNote().isEmpty()) {
                newExamClass.setNote(examClass.getNote());
            }

            newExamClass = this.save(newExamClass);
            examClassDetailService.save(new ExamClassDetail(newExamClass.getClassId(), examClass.getNumberStudent()));
        });

        return listError;
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

        return examClassRepository.saveAndFlush(examClass);
    }

    public ResponseEntity<?> division(Integer id, List<Integer> examinersId) {
        Optional<ExamClasses> examClassesOptional = this.findByIdAndStatus(id, REGISTERED.ordinal());
        Optional<ExamClassDetail> examClassDetail = examClassDetailService.findByExamClassId(id);

        if (examClassesOptional.isEmpty() || examClassDetail.isEmpty()) {
            return new ResponseEntity<>("Lớp thi không tồn tại.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (examClassesOptional.get().getExamCode().isEmpty()) {
            return new ResponseEntity<>("Thông tin lớp thi chưa được thiết lập. Không thể phân công trông thi",
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }

        int numberStudent = examClassDetail.get().getNumberStudent();
        if (numberStudent >= 60 && examinersId.size() != 2 || numberStudent < 60 && examinersId.size() != 1) {
            return new ResponseEntity<>("Số cán bộ coi thi không hợp lệ.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Iterable<ExamClassExaminerDetail> examClassExaminerDetails = examClassExaminerDetailService.findByExamClassId(id);
        if (((Collection<?>) examClassExaminerDetails).size() > 0) {
            examClassExaminerDetailService.removeAll(examClassExaminerDetails);
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

    public Optional<ExamClasses> findByClassId(Integer classId) {
        return examClassRepository.findByClassId(classId);
    }

    public List<ExamClasses> closeExamClass(String semester) {
        Iterable<ExamClasses> examClasses = examClassRepository.findBySemester(semester);
        examClasses.forEach(examClass -> {
            examClass.setStatus(ExamClasses.Status.CLOSED.ordinal());
        });

        return examClassRepository.saveAll(examClasses);
    }

    public Object getExaminersDivision(Integer examClassId) {
        return examClassRepository.getExaminersDivision(examClassId);
    }
}
