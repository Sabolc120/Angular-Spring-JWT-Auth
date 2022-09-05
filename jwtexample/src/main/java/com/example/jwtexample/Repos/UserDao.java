package com.example.jwtexample.Repos;

import com.example.jwtexample.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByuserName(String userName);
}
