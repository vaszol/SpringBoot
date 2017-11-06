package com.example.demo.dto;

import com.example.demo.Entity.User;

public class UserDTO {
    int id;
    String email;
    long created;

    public UserDTO(User user) {
        id = user.getId();
        email = user.getEmail();
        created = user.getCreated();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }
}
