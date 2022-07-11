package exam.management.be.repository;

import exam.management.be.model.ExamClassExaminerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamClassExaminerDetailRepository extends JpaRepository<ExamClassExaminerDetail, Integer> {
    @Query("SELECT a FROM ExamClassExaminerDetail a LEFT JOIN ExamClasses b ON b.id = a.examClassId " +
            "WHERE a.lecturerId = ?1 AND b.semester = ?2")
    Iterable<ExamClassExaminerDetail> findByLecturerIdAndSemester(Integer lecturerId, String semester);
}