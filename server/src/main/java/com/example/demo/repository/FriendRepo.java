package com.example.demo.repository;

import com.example.demo.entity.FriendsEntity;
import com.example.demo.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendRepo extends CrudRepository<FriendsEntity, Long> {

    List<FriendsEntity> findByStatusAndUser(String status, UserEntity user);
}
