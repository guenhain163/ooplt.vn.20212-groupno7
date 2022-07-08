package com.example.be.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class LecturerRequest {
    @NotNull
    @Length(min = 2, max = 50)
    private String name;

    @Length(min = 10, max = 12)
    private String phone;

    private String workRoom;

    @Length(min = 0, max = 2)
    private int role;

    private Long userId;

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
}
