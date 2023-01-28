package com.example.antifraud.controller;

import com.example.antifraud.model.DeleteUserResponse;
import com.example.antifraud.model.UserEntity;

import com.example.antifraud.model.UserResponse;
import com.example.antifraud.service.UserService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
@Validated
@AllArgsConstructor
public class UserController {

    UserService userService;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody UserEntity userEntity) {
        return userService.createUser(userEntity)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT));
    }

    @GetMapping("/list")
    public List<UserResponse> getAllUsers() {
        return userService.getListOfUsers();
    }

    @DeleteMapping("/user/{username}")
    @ResponseStatus(HttpStatus.OK)
    public DeleteUserResponse deleteUser(@PathVariable("username") String username) {
        if (userService.delete(username)) {
            return new DeleteUserResponse(username, "Deleted successfully!");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
