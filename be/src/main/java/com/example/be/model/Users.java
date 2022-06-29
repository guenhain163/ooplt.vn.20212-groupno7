package com.example.be.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "users", schema = "exam_management")
public class Users implements UserDetails {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "email", unique = true, length = 50)
    private String email;

    @Basic
    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Basic
    @Column(name = "role")
    private int role;

    @OneToOne(mappedBy = "usersByUserId")
    private Lecturers lecturers;

    protected static final int ROLE_USER = 1;
    protected static final int ROLE_MODERATOR = 2;
    protected static final int ROLE_ADMIN = 3;

    public Users(String email, String password) {
        super();
        this.email = email;
        this.password = password;
        this.role = 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
