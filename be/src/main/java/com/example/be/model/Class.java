package com.example.be.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "classes", schema = "exam_management")
public class Class {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "code")
    private String code;

    @Basic
    @Column(name = "group")
    private int group;

    @Basic
    @Column(name = "module_id")
    private Integer moduleId;

    @OneToMany(mappedBy = "classesByClassId")
    private Collection<ExamClass> examClassesById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
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
        Class that = (Class) o;
        return id == that.id && group == that.group && Objects.equals(code, that.code) && Objects.equals(moduleId, that.moduleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, group, moduleId);
    }

    public Collection<ExamClass> getExamClassesById() {
        return examClassesById;
    }

    public void setExamClassesById(Collection<ExamClass> examClassesById) {
        this.examClassesById = examClassesById;
    }
}
