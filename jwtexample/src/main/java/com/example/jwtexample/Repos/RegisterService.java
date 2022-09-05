package com.example.jwtexample.Repos;

import com.example.jwtexample.Entities.UserEntity;

public interface RegisterService {

    UserEntity registerUser(String username, String password);
}
