package exam.management.be.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserRequest {
    @NotNull
    @Email
    @Length(min = 6, max = 50)
    private String email;

    @NotNull
    @Length(min = 6, max = 32)
    private String password;

    @NotNull
    @Length(min = 6, max = 32)
    private String confirmPassword;

    private int role = Role.USER.ordinal();

    enum Role {
        USER,
        MODERATOR,
        ADMIN
    }

    public String getEmail() {
        return email;
    }

    public void setEmail() {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}
