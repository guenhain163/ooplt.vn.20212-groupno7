package com.example.be.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "exam_class_examiner_detail", schema = "exam_management")
public class ExamClassExaminerDetail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "exam_class_id")
    private Integer examClassId;

    @Basic
    @Column(name = "lecturer_id")
    private Integer lecturerId;

    @Basic
    @Column(name = "cost")
    private Integer cost;

    @Basic
    @Column(name = "status")
    private Integer status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getExamClassId() {
        return examClassId;
    }

    public void setExamClassId(Integer examClassId) {
        this.examClassId = examClassId;
    }

    public Integer getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Integer lecturerId) {
        this.lecturerId = lecturerId;
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
        ExamClassExaminerDetail that = (ExamClassExaminerDetail) o;
        return id == that.id && Objects.equals(examClassId, that.examClassId) && Objects.equals(lecturerId, that.lecturerId) && Objects.equals(cost, that.cost) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, examClassId, lecturerId, cost, status);
    }
}
