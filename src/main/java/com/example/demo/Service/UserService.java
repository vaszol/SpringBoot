package com.example.demo.Service;

import com.example.demo.Dao.UserDao;
import com.example.demo.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collection;

@Service
public class UserService {

    private SecureRandom random = new SecureRandom();
    @Autowired
    private UserDao userDao;

    public Collection<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void insertUser(User user) {
        if (userDao.getUserByMail(user.getEmail()) != null) {
            throw new Error();
        }

        user.setCreated(System.currentTimeMillis());
        user.setToken(new BigInteger(130, random).toString(32));
        userDao.insertUser(user);
    }

    public User getUserByToken(String token) {
        return userDao.getUserByToken(token);
    }

    public User login(User newUser) {
        User user = userDao.getUserByMail(newUser.getEmail());
        if (user.getPassword().equals(newUser.getPassword()))
            return user;
        return null;
    }
}
