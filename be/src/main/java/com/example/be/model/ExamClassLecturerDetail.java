package com.example.be.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "exam_class_lecturer_detail", schema = "exam_management")
public class ExamClassLecturerDetail {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "number_student")
    private int numberStudent;

    @Basic
    @Column(name = "cost")
    private Integer cost;

    @Basic
    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "lecturer_id", referencedColumnName = "id", nullable = false)
    private Lecturers lecturersByLecturerId;

    @ManyToOne
    @JoinColumn(name = "exam_class_id", referencedColumnName = "id", nullable = false)
    private ExamClasses examClassesByExamClassId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamClassLecturerDetail that = (ExamClassLecturerDetail) o;
        return id == that.id && numberStudent == that.numberStudent && Objects.equals(cost, that.cost) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberStudent, cost, status);
    }
}
