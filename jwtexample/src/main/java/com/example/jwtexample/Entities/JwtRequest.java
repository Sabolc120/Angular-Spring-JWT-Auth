package com.example.jwtexample.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest {

    private String authority;

    private String userName;

    private String userPassword;

}
