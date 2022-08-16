package com.example.demo.Service;

import com.example.demo.Modele.User;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Exception.UserAlreadyExistException;
import com.example.demo.Exception.UserNotFindException;
import com.example.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public UserEntity registration (UserEntity user) throws UserAlreadyExistException {
        if (userRepo.findAllByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistException("Пользователь c таким email уже существует!");
        }
        return userRepo.save(user);
    }

    public User getOne (Long id) throws UserNotFindException {
        UserEntity user = userRepo.findById(id).get();
        if(user == null)
            throw new UserNotFindException("Пользователь не был найден!");
        return User.toModel(user);
    }

    public UserEntity getUser (String email, String password) throws UserNotFindException {
        UserEntity user = userRepo.findByEmailAndPassword(email, password);
        if(user == null)
            throw new UserNotFindException("Не корректно введён логин либо пароль!");
        return user;
    }

    public Long delete (Long id) {
        userRepo.deleteById(id);
        return id;
    }

    public List<UserEntity> getUsers () {
        return userRepo.findAll();
    }

}

