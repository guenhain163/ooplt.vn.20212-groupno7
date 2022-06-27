package com.example.be.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "modules", schema = "exam_management")
public class Module {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "modulesByModuleId")
    private Collection<Class> classesById;

    @OneToMany(mappedBy = "modulesByModuleId")
    private Collection<Lecturer> lecturersById;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Module that = (Module) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code);
    }

    public Collection<Class> getClassesById() {
        return classesById;
    }

    public void setClassesById(Collection<Class> classesById) {
        this.classesById = classesById;
    }

    public Collection<Lecturer> getLecturersById() {
        return lecturersById;
    }

    public void setLecturersById(Collection<Lecturer> lecturersById) {
        this.lecturersById = lecturersById;
    }
}
