package exam.management.be.request;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class ImportExamClassRequest {
    @NotNull(message = "Mã lớp không được bỏ trống.")
    private String code;

    @NotNull(message = "Mã học phần không được bỏ trống.")
    private String codeModule;

    @NotNull(message = "Tên học phần không được bỏ trống.")
    private String nameModule;

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

    @NotNull(message = "SLĐK không được bỏ trống.")
    private Integer numberStudent;

    private String note;

    @NotNull(message = "Nhóm không được bỏ trống.")
    private String group;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeModule() {
        return codeModule;
    }

    public void setCodeModule(String codeModule) {
        this.codeModule = codeModule;
    }

    public String getNameModule() {
        return nameModule;
    }

    public void setNameModule(String nameModule) {
        this.nameModule = nameModule;
    }

    public void setExamShift(String examShift) {
        this.examShift = examShift;
    }

    public String getExamShift() {
        return examShift;
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
}
