package com.example.be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

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
    private Date registered_exam;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="module_id")
    @JsonBackReference
    private Modules modulesByModuleId;

    @OneToMany(mappedBy = "classesByClassId")
    @JsonManagedReference
    private Collection<ExamClasses> examClassesById;

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
        return registered_exam;
    }

    public void setRegistered_exam(Date date) {
        this.registered_exam = date;
    }
}
