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
            "LEFT JOIN Classes b ON a.classId = b.id " +
            "LEFT JOIN Modules c ON b.moduleId = c.id " +
            "LEFT JOIN ExamClassExaminerDetail f ON f.lecturerId = b.lecturerId " +
            "LEFT JOIN ExamClassDetail d ON d.examClassId = a.id " +
            "LEFT JOIN Lecturers e ON e.id = b.lecturerId " +
            "WHERE e.userId = ?1 AND a.semester = ?2 AND e.role IN ?3")
    List<Map<String, Object>> findAllByUserIdAndSemesterAndRole(Integer id, String semester, List<Integer> roles);

    @Query("SELECT a FROM ExamClasses a INNER JOIN Classes b ON b.id = a.classId WHERE b.lecturerId = ?1")
    Iterable<ExamClasses> findByLectureId(Integer lectureId);

    @Query("SELECT a FROM ExamClasses a LEFT JOIN Classes b ON b.id = a.classId WHERE b.lecturerId = ?1 AND a.semester = ?2")
    Iterable<ExamClasses> findByLectureIdAndSemester(Integer lectureId, String semester);

    Optional<ExamClasses> findByClassId(Integer id);

    Optional<ExamClasses> findByExamCode(String examCode);

    Optional<ExamClasses> findTopByOrderByExamCodeAsc();

    Iterable<ExamClasses> findBySemester(String semester);

    @Query("SELECT NEW map(b.lecturerId as lecturerId, c.name as name, d.email as email) FROM ExamClasses a " +
            "INNER JOIN ExamClassExaminerDetail b ON b.examClassId = a.id " +
            "INNER JOIN Lecturers c ON c.id = b.lecturerId " +
            "INNER JOIN Users d ON d.id = c.userId " +
            "WHERE a.id = ?1")
    List<Map<String, Object>> getExaminersDivision(Integer id);
}