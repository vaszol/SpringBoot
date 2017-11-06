package com.example.demo.Dao;

import com.example.demo.Entity.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDao {
    private static Map<Integer, User> users;

    static {
        users = new HashMap() {
            {
                put(0, new User(0, "admin", "admin"));
                put(1, new User(1, "user1", "user1"));
            }
        };
    }

    public Collection<User> getAllUsers() {
        return this.users.values();
    }

    public void insertUser(User user) {
        int id = 0;
        this.users.keySet();
        for (int i : this.users.keySet()) {
            if (i == id) {
                id = ++i;
            }
        }
        user.setId(id);
        this.users.put(id, user);
    }

    public User getUserByToken(String token) {
        User user = null;
        for (User u : this.users.values()) {
            if (u.getToken().equals(token)) user = u;
        }
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    public User getUserByMail(String email) {
        for (User u : this.users.values()) {
            if (u.getEmail().equals(email)) return u;
        }
        return null;
    }
}
