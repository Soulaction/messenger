package com.example.demo.controllers;

import com.example.demo.entity.FriendsEntity;
import com.example.demo.exception.FriendNotFoundException;
import com.example.demo.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @PostMapping(value = "/add")
    public ResponseEntity addFriend(@RequestBody FriendsEntity friendsEntity,
                                    @RequestParam Long id) {
        try {
            return ResponseEntity.ok(friendService.addFriend(friendsEntity, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!," + e.getMessage());
        }
    }

    @GetMapping("/myFriends")
    public ResponseEntity getFriends(@RequestParam Long id) {

        String status = "Добавлен";
        try {
            return ResponseEntity.ok(friendService.getFriends(id, status));
        } catch (FriendNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllFriends() {
        try {
            return ResponseEntity.ok(friendService.allFriends());
        } catch (FriendNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    @PostMapping("/accept")
    public ResponseEntity acceptUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(friendService.acceptUser(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }
}