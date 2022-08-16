package com.example.demo.Repository;

import com.example.demo.Entities.FriendsEntity;
import com.example.demo.Entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendRepo extends CrudRepository<FriendsEntity, Long> {

    List<FriendsEntity> findByStatusAndUser(String status, UserEntity user);
}
