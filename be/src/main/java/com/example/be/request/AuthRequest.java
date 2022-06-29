package com.example.be.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class AuthRequest {
    @NotNull
    @Email
    @Length(min = 6, max = 50)
    private String email;

    @NotNull @Length(min = 5, max = 32)
    private String password;

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
}
