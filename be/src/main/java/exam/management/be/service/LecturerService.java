package exam.management.be.service;

import exam.management.be.model.Classes;
import exam.management.be.model.Lecturers;
import exam.management.be.model.Speciality;
import exam.management.be.model.Users;
import exam.management.be.repository.LecturerRepository;
import exam.management.be.request.CreateExaminerRequest;
import exam.management.be.request.CreateLectureRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class LecturerService implements BaseService<Lecturers> {
    @Autowired
    private LecturerRepository lecturerRepository;
    @Autowired
    private ClassService classService;
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

    public ResponseEntity<?> listOfExaminersIsFree(Date date, Integer examShift, Integer examClassId) {
        Optional<Classes> classOptional =
                classService.findByExamClassId(examClassId);

        if (classOptional.isPresent()) {
            Integer lecturerId = classOptional.get().getLecturerId();
            return new ResponseEntity<>(lecturerRepository.findExaminersIsFree(date, examShift, lecturerId),
                    HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public List<Map<String, Object>> getAllLecturersOrExaminers(List<Integer> roles) {
        List<Lecturers> lecturerList = this.findByRoleIn(roles);

        List<Map<String, Object>> resultsList = new ArrayList<Map<String, Object>>();
        for (Lecturers lecturer : lecturerList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", lecturer.getId());
            map.put("name", lecturer.getName());
            map.put("phone", lecturer.getPhone());
            map.put("workRoom", lecturer.getWorkRoom());
            Users user = lecturer.getUsers();
            map.put("email", user.getEmail());
            List<Integer> moduleIdList = lecturer.getSpecialitiesById().stream().map(Speciality::getModuleId).toList();
            map.put("modules", moduleService.listModuleNamesById(moduleIdList));

            resultsList.add(map);
        }

        return resultsList;
    }

    public ResponseEntity<Lecturers> createLecturers(CreateLectureRequest lecturer) {
        if (userService.findByEmail(lecturer.getEmail()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

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

        return new ResponseEntity<>(newLecturer, HttpStatus.OK);
    }

    public ResponseEntity<Lecturers> createExaminers(CreateExaminerRequest examiner) {
        if (userService.findByEmail(examiner.getEmail()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Users user = userService.createDefault(examiner.getEmail());

        Lecturers newExaminer = this.save(new Lecturers(
                examiner.getName(),
                examiner.getPhone(),
                examiner.getWorkRoom(),
                user.getId(),
                Lecturers.Roles.EXAMINER.ordinal()
        ));

        if (examiner.getModules() != null && !examiner.getModules().isEmpty()) {
            ArrayList<Speciality> specialities = new ArrayList<>();
            for (Integer moduleId : examiner.getModules()) {
                Speciality speciality = new Speciality(newExaminer.getId(), moduleId);
                specialities.add(speciality);
            }

            specialityService.save(specialities);
        }

        return new ResponseEntity<>(newExaminer, HttpStatus.OK);
    }

    public List<Map<String, Object>> getExaminersByExamClassId(Integer examClassId) {
        return lecturerRepository.findByExamClassId(examClassId);
    }

    public List<Map<String, Object>> getLecturersOrExaminersBySemester(List<Integer> roleList) {
        return lecturerRepository.findByRole(roleList);
    }

    public Lecturers update(Integer id, Map<Object, Object> fields) {
        Optional<Lecturers> lecturer = lecturerRepository.findById(id);

        if (lecturer.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Lecturers.class, (String) key);
                assert field != null;
                field.setAccessible(true);
                ReflectionUtils.setField(field, lecturer.get(), value);
            });
            return lecturerRepository.saveAndFlush(lecturer.get());
        }

        return null;
    }
}
