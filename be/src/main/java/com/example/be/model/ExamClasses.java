package com.example.be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "exam_classes", schema = "exam_management")
public class ExamClasses {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "exam_code", length = 45)
    private String examCode;

    @Basic
    @Column(name = "class_id", nullable = false)
    private Integer classId;

    @Basic
    @Column(name = "exam_shift")
    private Integer examShift;

    @Basic
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private Date date;

    @Basic
    @Column(name = "week", length = 255)
    private String week;

    @Basic
    @Column(name = "opening_period", length = 255)
    private String openingPeriod;

    @Basic
    @Column(name = "exam_group")
    private String examGroup;

    @Basic
    @Column(name = "room", length = 255)
    private String room;

    @Basic
    @Column(name = "status")
    private Integer status;

    @Basic
    @Column(name = "note", length = 255)
    private String note;

    @Basic
    @Column(name = "semester", length = 10)
    private String semester;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "examClassesByExamClassId")
    private ExamClassDetail examClassDetailsById;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "examClassesByExamClassId")
    private Set<ExamClassExaminerDetail> examClassExaminerDetailsById = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    private Classes classesByClassId;

    public enum Status {
        NEW,
        REGISTERED,
        PAID,
        CLOSED
    }

    public ExamClasses(Integer classId, Integer examShift, Date date, String week, String openingPeriod, String room) {
        this.classId = classId;
        this.examShift = examShift;
        this.date = date;
        this.week = week;
        this.openingPeriod = openingPeriod;
        this.room = room;
    }

    public ExamClasses(Integer classId, Integer examShift, Date date, String week, String openingPeriod, String room, String note) {
        this(classId, examShift, date, week, openingPeriod, room);
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassId() {
        return classId;
    }

    public String getExamCode() {
        return examCode;
    }

    public void setExamCode(String examCode) {
        this.examCode = examCode;
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

    public String getExamGroup() {
        return examGroup;
    }

    public void setExamGroup(String examGroup) {
        this.examGroup = examGroup;
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

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamClasses that = (ExamClasses) o;
        return Objects.equals(id, that.id)
                && Objects.equals(examShift, that.examShift)
                && Objects.equals(date, that.date)
                && Objects.equals(week, that.week) 
                && Objects.equals(openingPeriod, that.openingPeriod) 
                && Objects.equals(room, that.room)
                && Objects.equals(status, that.status)
                && Objects.equals(examCode, that.examCode)
                && Objects.equals(examGroup, that.examGroup)
                && Objects.equals(semester, that.semester)
                && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, examShift, date, week, openingPeriod, room, examGroup, status, note, semester, examCode);
    }


    public Set<ExamClassExaminerDetail> getExamClassExaminerDetailsById() {
        return examClassExaminerDetailsById;
    }

    public void setExamClassExaminerDetailsById(Set<ExamClassExaminerDetail> examClassExaminerDetailsById) {
        this.examClassExaminerDetailsById = examClassExaminerDetailsById;
    }

    public ExamClassDetail getExamClassDetailsById() {
        return examClassDetailsById;
    }

    public void setExamClassDetailsById(ExamClassDetail examClassDetailsById) {
        this.examClassDetailsById = examClassDetailsById;
    }

    public Classes getClassesByClassId() {
        return classesByClassId;
    }

    public void setClassesByClassId(Classes classesByClassId) {
        this.classesByClassId = classesByClassId;
    }
}
