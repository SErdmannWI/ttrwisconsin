package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller;


import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.UserRequest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.UserResponse;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.UserRecord;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.service.UserService;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserRecord>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserRecord> getUserById(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/newUser")
    public ResponseEntity<UserRecord> createNewUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.createNewUser(userRequest));
    }
}
