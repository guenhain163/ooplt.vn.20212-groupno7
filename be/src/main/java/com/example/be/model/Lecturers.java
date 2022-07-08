package com.example.be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Lecturers {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "work_room")
    private String workRoom;

    /**
     * 0 - Examiner, 1 - Lecturer, 2 - All
     */
    @Basic
    @Column(name = "role")
    private int role;

    @Basic
    @Column(name = "user_id")
    private Long userId;

    @OneToOne(targetEntity = Users.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Users usersByUserId;

    @OneToMany(mappedBy = "lecturersByLecturerId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ExamClassLecturerDetail> examClassLecturerDetailsById;

    @OneToMany(mappedBy = "lecturersByLecturerId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ExamClassExaminerDetail> examClassExaminerDetailsById;

    @OneToMany(mappedBy = "lecturersByLecturerId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Speciality> specialitiesById = new HashSet<>();

    public enum Roles {
        EXAMINER,
        LECTURER,
        ALL
    }

    public Lecturers() {

    }

    public Lecturers(String name, String phone, String workRoom, Long userId, Integer role) {
        this.name = name;
        this.phone = phone;
        this.workRoom = workRoom;
        this.userId = userId;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecturers lecturers = (Lecturers) o;
        return id == lecturers.id && role == lecturers.role && Objects.equals(name, lecturers.name) && Objects.equals(phone, lecturers.phone) && Objects.equals(workRoom, lecturers.workRoom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, workRoom, role);
    }

    public Users getUsers() {
        return usersByUserId;
    }

    public void setUsers(Users users) {
        this.usersByUserId = users;
    }

    public Collection<ExamClassExaminerDetail> getExamClassExaminerDetailsById() {
        return examClassExaminerDetailsById;
    }

    public void setExamClassExaminerDetailsById(Set<ExamClassExaminerDetail> examClassExaminerDetailsById) {
        this.examClassExaminerDetailsById = examClassExaminerDetailsById;
    }

    public Collection<ExamClassLecturerDetail> getExamClassLecturerDetailsById() {
        return examClassLecturerDetailsById;
    }

    public void setExamClassLecturerDetailsById(Set<ExamClassLecturerDetail> examClassLecturerDetailsById) {
        this.examClassLecturerDetailsById = examClassLecturerDetailsById;
    }

    public Set<Speciality> getSpecialitiesById() {
        return specialitiesById;
    }

    public void setSpecialitiesById(Set<Speciality> specialitiesById) {
        this.specialitiesById = specialitiesById;
    }
}
