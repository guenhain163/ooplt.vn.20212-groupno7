package com.example.be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "classes", schema = "exam_management")
public class Classes {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "code", unique = true)
    private String code;

    @Basic
    @Column(name = "module_id")
    private Integer moduleId;

    @Basic
    @Column(name = "lecturer_id")
    private Integer lecturerId;

    @Basic
    @Column(name = "registered_exam")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registeredExam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="module_id", insertable = false, updatable = false)
    @JsonBackReference
    private Modules modulesByModuleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lecturer_id", insertable = false, updatable = false)
    @JsonBackReference
    private Lecturers lecturersByLecturerId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "classesByClassId")
    @JsonManagedReference
    private Set<ExamClasses> examClassesById = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getRegisteredExam() {
        return registeredExam;
    }

    public void setRegisteredExam(Date date) {
        this.registeredExam = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classes that = (Classes) o;
        return Objects.equals(id, that.id) && Objects.equals(code, that.code)
                && Objects.equals(moduleId, that.moduleId) && Objects.equals(lecturerId, that.lecturerId)
                && Objects.equals(registeredExam, that.registeredExam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, moduleId, lecturerId, registeredExam);
    }

    public Set<ExamClasses> getExamClassesById() {
        return examClassesById;
    }

    public void setExamClassesById(Set<ExamClasses> examClasses) {
        this.examClassesById = examClasses;
    }

    public Lecturers getLecturersByLecturerId() {
        return lecturersByLecturerId;
    }

    public void setLecturersByLecturerId(Lecturers lecturersByLecturerId) {
        this.lecturersByLecturerId = lecturersByLecturerId;
    }

    public Modules getModulesByModuleId() {
        return modulesByModuleId;
    }

    public void setModulesByModuleId(Modules modulesByModuleId) {
        this.modulesByModuleId = modulesByModuleId;
    }
}
