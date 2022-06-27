package com.example.be.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "lecturers", schema = "exam_management")
public class Lecturer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "work_room")
    private String workRoom;

    @Basic
    @Column(name = "module_id")
    private Integer moduleId;

    @OneToMany(mappedBy = "lecturersByLecturerId")
    private Collection<ExamClassLecturerDetail> examClassLecturerDetailsById;

    @ManyToOne
    @JoinColumn(name = "module_id", referencedColumnName = "id")
    private Module modulesByModuleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkRoom() {
        return workRoom;
    }

    public void setWorkRoom(String workRoom) {
        this.workRoom = workRoom;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecturer that = (Lecturer) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(workRoom, that.workRoom) && Objects.equals(moduleId, that.moduleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, email, workRoom, moduleId);
    }

    public Collection<ExamClassLecturerDetail> getExamClassLecturerDetailsById() {
        return examClassLecturerDetailsById;
    }

    public void setExamClassLecturerDetailsById(Collection<ExamClassLecturerDetail> examClassLecturerDetailsById) {
        this.examClassLecturerDetailsById = examClassLecturerDetailsById;
    }

    public Module getModulesByModuleId() {
        return modulesByModuleId;
    }

    public void setModulesByModuleId(Module modulesByModuleId) {
        this.modulesByModuleId = modulesByModuleId;
    }
}
