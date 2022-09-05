package com.example.jwtexample.Services;

import com.example.jwtexample.Repos.RegisterService;
import com.example.jwtexample.Repos.UserDao;
import com.example.jwtexample.Entities.UserEntity;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements RegisterService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity register(UserEntity userEntity){
        passwordEncoder.encode(userEntity.getUserPassword());
        return userDao.save(userEntity);
    }
    //DEFAULT ADMIN USER
    public void initRolesAndUser(){

        UserEntity adminUser = new UserEntity();
        adminUser.setId(1L);
        adminUser.setUserName("admin");
        adminUser.setUserPassword(getEncodedPassword("admin"));
        adminUser.setAuthorities("ADMIN");
        userDao.save(adminUser);
    }
    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }

    @Override
    public UserEntity registerUser(String userName, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);
        userEntity.setUserPassword(passwordEncoder.encode(password));
        userEntity.setAuthorities("USER");
        return userEntity;
    }
}
