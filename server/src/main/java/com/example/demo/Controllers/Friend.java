package com.example.demo.Controllers;

import com.example.demo.Entities.FriendsEntity;
import com.example.demo.Exception.FriendNotFoundException;
import com.example.demo.Service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend")
public class Friend {

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