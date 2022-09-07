package com.example.demo.controllers;

import com.example.demo.entity.UserEntity;
import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.exception.UserNotFindException;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registreted (@ModelAttribute UserEntity user){
        try {

            return ResponseEntity.ok(userService.registration(user));
        } catch (UserAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Пользователя не удалось зарегестрировать!");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne (@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.getOne(id));
        } catch (UserNotFindException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    @GetMapping()
    public ResponseEntity getOne (@RequestParam String email, @RequestParam String password){
        try {
            return ResponseEntity.ok(userService.getUser(email, password));
        } catch (UserNotFindException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser (@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.delete(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    @GetMapping("/all")
    public ResponseEntity getUsers () {
        try{
            return ResponseEntity.ok(userService.getUsers());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }
    //sla4g
}
