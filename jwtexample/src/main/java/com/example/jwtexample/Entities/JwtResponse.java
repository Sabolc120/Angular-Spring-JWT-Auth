package com.example.jwtexample.Entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtResponse {

    private UserEntity userEntity;
    private String jwtToken;

    public JwtResponse(UserEntity userEntity, String jwtToken) {
        this.userEntity = userEntity;
        this.jwtToken = jwtToken;
    }
}
