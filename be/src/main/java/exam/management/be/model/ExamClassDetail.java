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
@Table(name = "exam_class_detail", schema = "exam_management")
public class ExamClassDetail {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "number_student")
    private int numberStudent;

    @Basic
    @Column(name = "cost")
    private Integer cost;

    @Basic
    @Column(name = "exam_class_id")
    private Integer examClassId;

    @Basic
    @Column(name = "printing_cost")
    private Integer printingCost;

    @Basic
    @Column(name = "examination_cost")
    private Integer examinationCost;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_class_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    private ExamClasses examClassesByExamClassId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExamClassId() {
        return examClassId;
    }

    public void setExamClassId(Integer examClassId) {
        this.examClassId = examClassId;
    }

    public int getNumberStudent() {
        return numberStudent;
    }

    public void setNumberStudent(int numberStudent) {
        this.numberStudent = numberStudent;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getExaminationCost() {
        return examinationCost;
    }

    public void setExaminationCost(Integer examinationCost) {
        this.examinationCost = examinationCost;
    }

    public Integer getPrintingCost() {
        return printingCost;
    }

    public void setPrintingCost(Integer printingCost) {
        this.printingCost = printingCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamClassDetail that = (ExamClassDetail) o;
        return id == that.id && Objects.equals(cost, that.cost) && Objects.equals(numberStudent, that.numberStudent)
                && Objects.equals(examClassId, that.examClassId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost, numberStudent, examClassId);
    }

    public ExamClasses getExamClassesByExamClassId() {
        return examClassesByExamClassId;
    }

    public void setExamClassesByExamClassId(ExamClasses examClassesByExamClassId) {
        this.examClassesByExamClassId = examClassesByExamClassId;
    }
}
