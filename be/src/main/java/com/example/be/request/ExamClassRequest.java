package com.example.be.request;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

public class ExamClassRequest {
    @NotNull
    private Long classId;

    @NotNull
    private Integer examShift;

    @NotNull
    private Date date;

    @NotNull
    private String week;

    @NotNull
    private String openingPeriod;

    @NotNull
    private String room;

    /**
     * 1 - New
     * 2 - Completed
     * 3 - Closed
     */
    private Integer status;

    private String note;

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
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
}
