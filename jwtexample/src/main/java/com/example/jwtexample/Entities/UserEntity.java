package com.example.jwtexample.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identifier", unique = true)
    private Long id;

    private String userName;

    private String userPassword;

    private String authorities;

}
