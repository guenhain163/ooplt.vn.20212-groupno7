package com.example.be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private Long id;

    @Basic
    @Column(name = "code", unique = true)
    private String code;

    @Basic
    @Column(name = "group")
    private int group;

    @Basic
    @Column(name = "registered_exam")
    @CreationTimestamp
    private Date registeredExam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="module_id")
    @JsonBackReference
    private Modules modulesByModuleId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "classesByClassId")
//    @JsonManagedReference
    private Set<ExamClasses> examClassesById = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getRegistered_exam() {
        return registeredExam;
    }

    public void setRegistered_exam(Date date) {
        this.registeredExam = date;
    }

    public Set<ExamClasses> getExamClasses() {
        return examClassesById;
    }

    public void setOrderItems(Set<ExamClasses> examClasses) {
        this.examClassesById = examClasses;
    }
}
