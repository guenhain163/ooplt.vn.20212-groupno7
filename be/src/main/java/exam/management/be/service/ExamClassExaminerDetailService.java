package exam.management.be.service;

import exam.management.be.model.ExamClassExaminerDetail;
import exam.management.be.repository.ExamClassExaminerDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamClassExaminerDetailService implements BaseService<ExamClassExaminerDetail> {
    @Autowired
    private ExamClassExaminerDetailRepository examClassExaminerDetailRepository;

    @Override
    public Iterable<ExamClassExaminerDetail> findAll() {
        return examClassExaminerDetailRepository.findAll();
    }

    @Override
    public Optional<ExamClassExaminerDetail> findById(Integer id) {
        return examClassExaminerDetailRepository.findById(id);
    }

    @Override
    public ExamClassExaminerDetail save(ExamClassExaminerDetail examClassExaminerDetail) {
        return examClassExaminerDetailRepository.save(examClassExaminerDetail);
    }

    @Override
    public ExamClassExaminerDetail update(ExamClassExaminerDetail examClassExaminerDetail) {
        return examClassExaminerDetailRepository.saveAndFlush(examClassExaminerDetail);
    }

    @Override
    public void remove(Integer id) {
        examClassExaminerDetailRepository.deleteById(id);
    }

    public Iterable<ExamClassExaminerDetail> save(Iterable<ExamClassExaminerDetail> examClassExaminerDetails) {
        return examClassExaminerDetailRepository.saveAll(examClassExaminerDetails);
    }

    public Iterable<ExamClassExaminerDetail> findByLectureIdAndSemester(Integer lecturerId, String semester) {
        return examClassExaminerDetailRepository.findByLecturerIdAndSemester(lecturerId, semester);
    }
}
