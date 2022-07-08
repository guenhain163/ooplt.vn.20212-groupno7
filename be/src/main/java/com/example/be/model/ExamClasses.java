package com.example.be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "exam_classes", schema = "exam_management")
public class ExamClasses {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "exam_shift", nullable = true)
    private Integer examShift;

    @Basic
    @Column(name = "date", nullable = true)
    private Date date;

    @Basic
    @Column(name = "week", nullable = true, length = 255)
    private String week;

    @Basic
    @Column(name = "opening_period", nullable = true, length = 255)
    private String openingPeriod;

    @Basic
    @Column(name = "room", nullable = true, length = 255)
    private String room;

    @Basic
    @Column(name = "status", nullable = true)
    private Integer status;

    @Basic
    @Column(name = "note", nullable = true, length = 255)
    private String note;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "examClassesByExamClassId")
    private Set<ExamClassLecturerDetail> examClassLecturerDetailsById = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "examClassesByExamClassId")
    private Set<ExamClassExaminerDetail> examClassExaminerDetailsById = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", referencedColumnName = "id")
//    @JsonBackReference
    private Classes classesByClassId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getExamShift() {
        return examShift;
    }

    public void setExamShift(int examShift) {
        this.examShift = examShift;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getOpeningPeriod() {
        return openingPeriod;
    }

    public void setOpeningPeriod(String openingPeriod) {
        this.openingPeriod = openingPeriod;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamClasses that = (ExamClasses) o;
        return id == that.id
                && Objects.equals(examShift, that.examShift)
                && Objects.equals(date, that.date)
                && Objects.equals(week, that.week) 
                && Objects.equals(openingPeriod, that.openingPeriod) 
                && Objects.equals(room, that.room)
                && Objects.equals(status, that.status)
                && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, examShift, date, week, openingPeriod, room, status, note);
    }


    public Set<ExamClassExaminerDetail> getExamClassExaminerDetailsById() {
        return examClassExaminerDetailsById;
    }

    public void setExamClassExaminerDetailsById(Set<ExamClassExaminerDetail> examClassExaminerDetailsById) {
        this.examClassExaminerDetailsById = examClassExaminerDetailsById;
    }

    public Set<ExamClassLecturerDetail> getExamClassLecturerDetailsById() {
        return examClassLecturerDetailsById;
    }

    public void setExamClassLecturerDetailsById(Set<ExamClassLecturerDetail> examClassLecturerDetailsById) {
        this.examClassLecturerDetailsById = examClassLecturerDetailsById;
    }

    public Classes getClassesByClassId() {
        return classesByClassId;
    }

    public void setClassesByClassId(Classes classesByClassId) {
        this.classesByClassId = classesByClassId;
    }
}
