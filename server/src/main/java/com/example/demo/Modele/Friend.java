package com.example.demo.Modele;


import com.example.demo.Entities.FriendsEntity;
import com.example.demo.Entities.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Friend {
    private Long id;
    private String name;
    private String surname;
    private String avatar;

    public static Friend toModel (FriendsEntity friend){
        Friend model = new Friend();
        model.setId(friend.getId());
        model.setSurname(friend.getUser().getSurname());
        model.setName(friend.getUser().getName());
        model.setAvatar(friend.getUser().getAvatar());
        return model;
    }

    public static List<Friend> ListToModel (List<FriendsEntity> friends){
        return friends.stream().map(Friend::toModel)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
