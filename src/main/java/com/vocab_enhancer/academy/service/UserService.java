package com.vocab_enhancer.academy.service;

import com.vocab_enhancer.academy.exceptions.ResourceNotFoundException;
import com.vocab_enhancer.academy.model.dto.userDto.*;
import com.vocab_enhancer.academy.model.entity.User;
import com.vocab_enhancer.academy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public UserResponse getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));

        return toResponse(user);
    }

    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public UserResponse add(UserCreateRequest userCreateRequest) {
        User user = User.builder()
                .name(userCreateRequest.name())
                .email(userCreateRequest.email())
                .hashPassword(userCreateRequest.password())
                .build();

        userRepository.save(user);

        return toResponse(user);
    }


}
