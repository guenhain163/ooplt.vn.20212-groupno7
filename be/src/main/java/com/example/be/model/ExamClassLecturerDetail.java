package com.example.be.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "exam_class_lecturer_detail", schema = "exam_management")
public class ExamClassLecturerDetail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "lecturer_id")
    private int lecturerId;

    @Basic
    @Column(name = "class_exam_id")
    private int classExamId;

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
    private Lecturer lecturersByLecturerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public int getClassExamId() {
        return classExamId;
    }

    public void setClassExamId(int classExamId) {
        this.classExamId = classExamId;
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
        return id == that.id && lecturerId == that.lecturerId && classExamId == that.classExamId && numberStudent == that.numberStudent && Objects.equals(cost, that.cost) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lecturerId, classExamId, numberStudent, cost, status);
    }

    public Lecturer getLecturersByLecturerId() {
        return lecturersByLecturerId;
    }

    public void setLecturersByLecturerId(Lecturer lecturersByLecturerId) {
        this.lecturersByLecturerId = lecturersByLecturerId;
    }
}
