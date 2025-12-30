package com.example.java_crud.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_crud.model.User;
import com.example.java_crud.service.UserService;
import com.example.java_crud.dtos.UserDto.LoginResponse;
import com.example.java_crud.dtos.UserDto.ProfileResponse;
import com.example.java_crud.dtos.UserDto.LoginRequestDto;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    // start ---- this is just for the simple check
    @GetMapping("/ping")
    public String pingServer() {
        return "Server is running!";
    }

    // end ----- this is the just for the simple check

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    // update the user details here
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return service.updateUser(id, user);
    }

    // login the user
    // @PostMapping("/login")
    // @postMapping("/login")
    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody LoginRequestDto loginRequestDto) {
        return service.loginUser(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    }

    // get the profile of the users
    @GetMapping("/profile/{id}")
    public ProfileResponse GetUserProfile(@PathVariable Long id) {
        return service.GetUserProfile(id);
    }

}
