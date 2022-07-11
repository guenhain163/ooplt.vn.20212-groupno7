package exam.management.be.repository;

import exam.management.be.model.ExamClasses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ExamClassRepository extends JpaRepository<ExamClasses, Integer> {
    Optional<ExamClasses> findByIdAndStatus(Integer id, Integer status);

    @Query("SELECT new map(a as classExam, c.name as nameModule, c.code as codeModule, " +
            "d.numberStudent as numberStudent, e.name as nameLecturer) FROM ExamClasses a " +
            "LEFT JOIN Classes b " +
            "   ON a.classId = b.id " +
            "LEFT JOIN Modules c " +
            "   ON b.moduleId = c.id " +
            "LEFT JOIN ExamClassDetail d " +
            "   ON d.examClassId = a.id " +
            "LEFT JOIN Lecturers e " +
            "   ON e.id = b.lecturerId")
    List<Map<String, Object>> getAllExamClasses();

    @Query("SELECT new map(a as classExam, c.name as nameModule, c.code as codeModule, " +
            "d.numberStudent as numberStudent, e.name as nameLecturer) FROM ExamClasses a " +
            "LEFT JOIN Classes b " +
            "   ON a.classId = b.id " +
            "LEFT JOIN Modules c " +
            "   ON b.moduleId = c.id " +
            "LEFT JOIN ExamClassDetail d " +
            "   ON d.examClassId = a.id " +
            "LEFT JOIN Lecturers e " +
            "   ON e.id = b.lecturerId " +
            "WHERE b.lecturerId = ?1 AND a.semester = ?2")
    List<Map<String, Object>> findAllByLecturerIdAndSemester(Integer lecturerId, String semester);

    @Query("SELECT a FROM ExamClasses a INNER JOIN Classes b ON b.id = a.classId WHERE b.lecturerId = ?1")
    Iterable<ExamClasses> findByLectureId(Integer lectureId);

    @Query("SELECT a FROM ExamClasses a LEFT JOIN Classes b ON b.id = a.classId WHERE b.lecturerId = ?1 AND a.semester = ?2")
    Iterable<ExamClasses> findByLectureIdAndSemester(Integer lectureId, String semester);
}