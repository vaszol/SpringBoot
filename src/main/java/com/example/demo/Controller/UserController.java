package com.example.demo.Controller;

import com.example.demo.Entity.Token;
import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import com.example.demo.dto.ErrorDTO;
import com.example.demo.dto.SuccesDTO;
import com.example.demo.dto.TokenDTO;
import com.example.demo.dto.WrapperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Collection<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public WrapperDTO register(@RequestBody User user) {
        userService.insertUser(user);

        TokenDTO responseBody = new TokenDTO();
        responseBody.setSuccess(true);
        responseBody.setMessage("");
        responseBody.setToken(user.getToken());
        return responseBody;
    }

    @RequestMapping(
            value = "/confirm",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public WrapperDTO confirm(@RequestBody Token token) {
        User user = userService.getUserByToken(token.getToken());

        SuccesDTO responseBody = new SuccesDTO(user);
        responseBody.setSuccess(true);
        responseBody.setMessage("");
        return responseBody;
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public WrapperDTO login(@RequestBody User newUser) {
        User user = userService.login(newUser);

        SuccesDTO responseBody = new SuccesDTO(user);
        responseBody.setSuccess(true);
        responseBody.setMessage("");
        return responseBody;
    }

    @ExceptionHandler(Exception.class)
    public WrapperDTO handleException(Exception e){
        ErrorDTO responseBody = new ErrorDTO();
        responseBody.setSuccess(false);
        responseBody.setMessage(e.getMessage());
        responseBody.setError(e.getMessage());
        return responseBody;
    }

}
