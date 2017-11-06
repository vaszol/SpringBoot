package com.example.demo.Entity;

import java.math.BigInteger;
import java.security.SecureRandom;

public class User {
    private int id;
    private String email;
    private String password;
    private long created;
    private String token;
    private SecureRandom random = new SecureRandom();

    public User() {
    }

    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
        created = System.currentTimeMillis();
        token = new BigInteger(130, random).toString(32);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
