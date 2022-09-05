package com.example.jwtexample.Controllers;

import com.example.jwtexample.Entities.UserEntity;
import com.example.jwtexample.Repos.RegisterService;
import com.example.jwtexample.Repos.UserDao;
import com.example.jwtexample.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;

@RestController
public class RegisterController {

    @Autowired
    public UserDao userDao;

    @Autowired
    public RegisterService registerService;

    @Autowired
    public UserService userService;

    @PostMapping("/register")
    public UserEntity registerUser(@RequestBody UserEntity userEntity){
        String userName = userEntity.getUserName();
        String password = userEntity.getUserPassword();

        UserEntity userEntity1 = userService.registerUser(userName, password);
        return userDao.save(userEntity1);
    }
}
