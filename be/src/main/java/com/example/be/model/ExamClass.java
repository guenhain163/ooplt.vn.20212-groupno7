package com.example.be.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "exam_classes", schema = "exam_management")
public class ExamClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "class_id")
    private Integer classId;

    @Basic
    @Column(name = "exam_shift")
    private int examShift;

    @Basic
    @Column(name = "date")
    private Date date;

    @Basic
    @Column(name = "week")
    private String week;

    @Basic
    @Column(name = "opening_period")
    private String openingPeriod;

    @Basic
    @Column(name = "room")
    private String room;

    @Basic
    @Column(name = "status")
    private Integer status;

    @Basic
    @Column(name = "note")
    private String note;

    @OneToMany(mappedBy = "examClassesByClassExamId")
    private Collection<ExamClassLecturerDetail> examClassLecturerDetailsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
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
        ExamClass that = (ExamClass) o;
        return id == that.id && examShift == that.examShift && Objects.equals(classId, that.classId) && Objects.equals(date, that.date) && Objects.equals(week, that.week) && Objects.equals(openingPeriod, that.openingPeriod) && Objects.equals(room, that.room) && Objects.equals(status, that.status) && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classId, examShift, date, week, openingPeriod, room, status, note);
    }

    public Collection<ExamClassLecturerDetail> getExamClassLecturerDetailsById() {
        return examClassLecturerDetailsById;
    }

    public void setExamClassLecturerDetailsById(Collection<ExamClassLecturerDetail> examClassLecturerDetailsById) {
        this.examClassLecturerDetailsById = examClassLecturerDetailsById;
    }
}
