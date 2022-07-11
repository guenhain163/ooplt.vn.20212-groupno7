package exam.management.be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "exam_class_examiner_detail", schema = "exam_management")
public class ExamClassExaminerDetail {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "lecturer_id")
    private Integer lecturerId;

    @Basic
    @Column(name = "exam_class_id")
    private Integer examClassId;

    @Basic
    @Column(name = "cost")
    private Integer cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecturer_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    private Lecturers lecturersByLecturerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_class_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    private ExamClasses examClassesByExamClassId;

    public ExamClassExaminerDetail(Integer examClassId, Integer lecturerId) {
        this.lecturerId = lecturerId;
        this.examClassId = examClassId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Integer lecturerId) {
        this.lecturerId = lecturerId;
    }

    public Integer getExamClassId() {
        return examClassId;
    }

    public void setExamClassId(Integer examClassId) {
        this.examClassId = examClassId;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamClassExaminerDetail that = (ExamClassExaminerDetail) o;
        return id == that.id && Objects.equals(cost, that.cost) && Objects.equals(lecturerId, that.lecturerId)
                && Objects.equals(examClassId, that.examClassId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost, lecturerId, examClassId);
    }

    public ExamClasses getExamClassesByExamClassId() {
        return examClassesByExamClassId;
    }

    public void setExamClassesByExamClassId(ExamClasses examClassesByExamClassId) {
        this.examClassesByExamClassId = examClassesByExamClassId;
    }

    public Lecturers getLecturersByLecturerId() {
        return lecturersByLecturerId;
    }

    public void setLecturersByLecturerId(Lecturers lecturersByLecturerId) {
        this.lecturersByLecturerId = lecturersByLecturerId;
    }
}
