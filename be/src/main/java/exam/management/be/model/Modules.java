package exam.management.be.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "modules", schema = "exam_management")
public class Modules {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "code", unique = true)
    private String code;

    @OneToMany(mappedBy = "modulesByModuleId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Classes> classesById = new HashSet<>();


    @OneToMany(mappedBy = "modulesByModuleId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Speciality> specialitiesById = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        Modules modules = (Modules) o;
        return id == modules.id && Objects.equals(code, modules.code) && Objects.equals(name, modules.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name);
    }

    public Set<Classes> getClassesById() {
        return classesById;
    }

    public void setOrderItems(Set<Classes> classes) {
        this.classesById = classes;
    }

    public Collection<Speciality> getSpecialitiesById() {
        return specialitiesById;
    }

    public void setSpecialitiesById(Set<Speciality> specialitiesById) {
        this.specialitiesById = specialitiesById;
    }
}
