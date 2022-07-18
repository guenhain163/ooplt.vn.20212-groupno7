package exam.management.be.request;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class ExamClassRequest {
    @NotNull(message = "Mã lớp không được bỏ trống.")
    private Integer classId;

    @NotNull(message = "Mã học phần không được bỏ trống.")
    private Integer moduleId;

    @NotNull(message = "Kíp không được bỏ trống.")
    private String examShift;

    @NotNull(message = "Ngày thi không được bỏ trống.")
    private Date date;

    @NotNull(message = "Tuần không được bỏ trống.")
    private String week;

    @NotNull(message = "Đợt mở không được bỏ trống.")
    private String openingPeriod;

    @NotNull(message = "Phòng thi không được bỏ trống.")
    private String room;

    @NotNull(message = "Status không được bỏ trống.")
    private Integer status;

    @NotNull(message = "SLĐK không được bỏ trống.")
    private Integer numberStudent;

    private String note;

    @NotNull(message = "Nhóm không được bỏ trống.")
    private String group;

    @NotNull(message = "Kỳ học không được bỏ trống.")
    private String semester;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getExamShift() {
        return examShift;
    }

    public void setExamShift(String examShift) {
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

    public Integer getNumberStudent() {
        return numberStudent;
    }

    public void setNumberStudent(Integer numberStudent) {
        this.numberStudent = numberStudent;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
