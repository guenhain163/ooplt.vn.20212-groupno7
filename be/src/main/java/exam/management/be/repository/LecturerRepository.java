package exam.management.be.repository;

import exam.management.be.model.Lecturers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturers, Integer > {
    List<Lecturers> findByRoleIn(List<Integer> roles);

    Optional<Lecturers> findByIdAndRoleIn(Integer id, List<Integer> roles);

    Optional<Lecturers> findByUserId(Integer userId);

    @Modifying
    @Query("SELECT NEW map(a.id as id, a.name as name, d.email as email) FROM Lecturers a " +
            "INNER JOIN Users d ON d.id = a.userId " +
            "WHERE a.id NOT IN " +
            "    (SELECT b.lecturerId FROM ExamClassExaminerDetail b " +
            "    LEFT JOIN ExamClasses c " +
            "ON b.examClassId = c.id " +
            "WHERE c.date = ?1 AND c.examShift = ?2) " +
            "    AND a.id <> ?3 AND a.role IN (0, 2)")
    List<Map<String, Object>> findExaminersIsFree(Date date, Integer examShift, Integer  lecturerId);

    @Query("SELECT NEW map(a.id as id, a.name as name, b.email as email) FROM Lecturers a " +
            "INNER JOIN Users b ON b.id = a.userId " +
            "INNER JOIN ExamClassExaminerDetail c ON c.lecturerId = a.id " +
            "WHERE c.examClassId = ?1")
    List<Map<String, Object>> findByExamClassId(Integer examClassId);

    @Query("SELECT NEW map(a.id as id, a.name as name, d.email as email) FROM Lecturers a " +
            "INNER JOIN Users d ON d.id = a.userId " +
            "WHERE a.role IN ?1")
    List<Map<String, Object>> findByRole(List<Integer> roles);
}
