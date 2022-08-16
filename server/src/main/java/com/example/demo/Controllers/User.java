package com.example.demo.Controllers;

import com.example.demo.Entities.UserEntity;
import com.example.demo.Exception.UserAlreadyExistException;
import com.example.demo.Exception.UserNotFindException;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class User {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registreted (@ModelAttribute UserEntity user){
        try {
            userService.registration(user);
            return ResponseEntity.ok("Пользователь зарегестрирован!");
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
