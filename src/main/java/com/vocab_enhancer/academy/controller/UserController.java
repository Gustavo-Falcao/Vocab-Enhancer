package com.vocab_enhancer.academy.controller;

import com.vocab_enhancer.academy.model.dto.userDto.UserCreateRequest;
import com.vocab_enhancer.academy.model.dto.userDto.UserResponse;
import com.vocab_enhancer.academy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> listarUser() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@RequestBody UserCreateRequest userCreateRequest) {
        return new ResponseEntity<>(userService.add(userCreateRequest), HttpStatus.CREATED);
    }


}
