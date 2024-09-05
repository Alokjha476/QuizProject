package com.quiz.controller;

import com.quiz.entity.User;
import com.quiz.exceptions.ServiceException;
import com.quiz.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public String userAdd(@RequestBody User user) {
        userService.addUser(user);
        return "User Add Successfully:" + user;

    }

    @GetMapping("/get/username/{username}")
    public ResponseEntity<Object> findUserByUsername(@PathVariable String username) {
        User users = userService.findUserByUsername(username);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {

        User user1 = userService.updateUser(user);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }


}
