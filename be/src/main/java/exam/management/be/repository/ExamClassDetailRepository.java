package exam.management.be.repository;

import exam.management.be.model.ExamClassDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamClassDetailRepository extends JpaRepository<ExamClassDetail, Integer> {
    Optional<ExamClassDetail> findByExamClassId(Integer examClassId);
}