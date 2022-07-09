package com.example.be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "exam_class_lecturer_detail", schema = "exam_management")
public class ExamClassLecturerDetail {
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
    @Column(name = "lecturer_id")
    private Integer lectureId;

    @Basic
    @Column(name = "exam_class_id")
    private Integer examClassId;

    @ManyToOne
    @JoinColumn(name = "lecturer_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    private Lecturers lecturersByLecturerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_class_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    private ExamClasses examClassesByExamClassId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLectureId() {
        return lectureId;
    }

    public void setLectureId(Integer lectureId) {
        this.lectureId = lectureId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamClassLecturerDetail that = (ExamClassLecturerDetail) o;
        return id == that.id && Objects.equals(cost, that.cost) && Objects.equals(numberStudent, that.numberStudent)
                && Objects.equals(lectureId, that.lectureId) && Objects.equals(examClassId, that.examClassId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost, numberStudent, lectureId, examClassId);
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
