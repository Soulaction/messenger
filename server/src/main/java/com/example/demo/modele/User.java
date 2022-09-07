package com.example.demo.modele;

import com.example.demo.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class User {

    private Long id;
    private String name;
    private String surname;
    private String avatar;

    public static User toModel (UserEntity user){
        User model = new User();
        model.setId(user.getId());
        model.setName(user.getName());
        model.setSurname(user.getSurname());
//        model.setAvatar(user.getAvatar());
        return model;
    }

    public static List<User> ListToModel (List<UserEntity> friends){
        return friends.stream().map(User::toModel)
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
