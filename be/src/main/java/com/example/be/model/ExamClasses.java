package com.example.be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "exam_classes", schema = "exam_management")
public class ExamClasses {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

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

    @OneToMany(mappedBy = "examClassesByExamClassId")
    private Collection<ExamClassLecturerDetail> examClassLecturerDetailsById;

    @OneToMany(mappedBy = "examClassesByExamClassId")
    private Collection<ExamClassExaminerDetail> examClassExaminerDetailsById;

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    @JsonBackReference
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
        return id == that.id && examShift == that.examShift && Objects.equals(date, that.date) && Objects.equals(week, that.week) && Objects.equals(openingPeriod, that.openingPeriod) && Objects.equals(room, that.room) && Objects.equals(status, that.status) && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, examShift, date, week, openingPeriod, room, status, note);
    }
}
