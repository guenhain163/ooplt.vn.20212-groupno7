package com.example.be.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Lecturers {
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
    @Column(name = "work_room")
    private String workRoom;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private Users usersByUserId;

    @OneToMany(mappedBy = "lecturersByLecturerId")
    private Collection<ExamClassLecturerDetail> examClassLecturerDetailsById;

    @OneToMany(mappedBy = "lecturersByLecturerId")
    private Collection<ExamClassExaminerDetail> examClassExaminerDetailsById;

    @OneToMany(mappedBy = "modulesByModuleId")
    @Column(nullable = true)
    @JsonManagedReference
    private Collection<Speciality> speciality;

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

    public String getWorkRoom() {
        return workRoom;
    }

    public void setWorkRoom(String workRoom) {
        this.workRoom = workRoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecturers lecturers = (Lecturers) o;
        return id == lecturers.id && Objects.equals(name, lecturers.name) && Objects.equals(phone, lecturers.phone)
                && Objects.equals(workRoom, lecturers.workRoom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, workRoom);
    }

//    public Collection<ExamClassLecturerDetail> getExamClassLecturerDetailsById() {
//        return examClassLecturerDetailsById;
//    }
//
//    public void setExamClassLecturerDetailsById(Collection<ExamClassLecturerDetail> examClassLecturerDetailsById) {
//        this.examClassLecturerDetailsById = examClassLecturerDetailsById;
//    }
}
