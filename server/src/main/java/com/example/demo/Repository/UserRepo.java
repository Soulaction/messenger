package com.example.demo.Repository;

import com.example.demo.Entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
    UserEntity findAllByEmail (String email);

    List<UserEntity> findAll();

    UserEntity findByEmailAndPassword(String email, String password);
}
