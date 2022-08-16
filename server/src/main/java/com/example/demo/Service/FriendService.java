package com.example.demo.Service;

import com.example.demo.Entities.FriendsEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Exception.FriendNotFoundException;
import com.example.demo.Modele.Friend;
import com.example.demo.Modele.User;
import com.example.demo.Repository.FriendRepo;
import com.example.demo.Repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.Modele.User.ListToModel;

@Slf4j
@Service
public class FriendService {
    @Autowired
    private FriendRepo friendRepo;
    @Autowired
    private UserRepo userRepo;
    public FriendsEntity addFriend (FriendsEntity friends, Long id) {
        UserEntity user = userRepo.findById(id).get();
        friends.setUser(user);
        return friendRepo.save(friends);
    }

    public List<Friend> getFriends (Long id, String status) throws FriendNotFoundException {
        UserEntity user = userRepo.findById(id).get();
        if((friendRepo.findByStatusAndUser(status, user)).size() == 0)
            throw new FriendNotFoundException("У вас пока нет добавленных друзей!");
        return Friend.ListToModel(friendRepo.findByStatusAndUser(status, user));
    }

    public List<Friend> allFriends () throws FriendNotFoundException {
        if(((List<FriendsEntity>) friendRepo.findAll()).size() == 0)
            throw new FriendNotFoundException("У вас пока нет добавленных друзей!");
        return Friend.ListToModel((List<FriendsEntity>) friendRepo.findAll());
    }

    public FriendsEntity acceptUser(Long id){
        FriendsEntity friend = friendRepo.findById(id).get();
        friend.setStatus("Добавлен");
        return friendRepo.save(friend);
    }
}
