package exam.management.be.request;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

public class ExamClassRequest {
    @NotNull
    private Integer classId;

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

    private String group;

    private String examCode;

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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }

    public String getExamCode() {
        return examCode;
    }
}
