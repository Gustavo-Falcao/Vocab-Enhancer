package com.vocab_enhancer.academy.service;

import com.vocab_enhancer.academy.exceptions.EmailAlreadyExistsException;
import com.vocab_enhancer.academy.exceptions.ResourceNotFoundException;
import com.vocab_enhancer.academy.model.dto.userDto.*;
import com.vocab_enhancer.academy.model.entity.User;
import com.vocab_enhancer.academy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return toResponse(findById(id));
    }

    private User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

    public void delete(long id) {
        userRepository.delete(findById(id));
    }

    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    private boolean emailAlreadyExistsInUsers(String email) {
        return userRepository.findAll().stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

    private boolean emailAlreadyExistsInUsers(String email, long id) {
        return userRepository.findAll().stream()
                .filter(user -> user.getId() != id)
                .anyMatch(user -> user.getEmail().equals(email));
    }

    public UserResponse add(UserCreateRequest userCreateRequest) {
        if(emailAlreadyExistsInUsers(userCreateRequest.email()))
            throw new EmailAlreadyExistsException("email já cadastrado");

        User user = User.builder()
                .name(userCreateRequest.name())
                .email(userCreateRequest.email())
                .hashPassword(userCreateRequest.password())
                .build();

        userRepository.save(user);

        return toResponse(user);
    }

    public void update(long id, UserUpdateRequest userUpdateRequest) {
        User user = findById(id);

        if(emailAlreadyExistsInUsers(userUpdateRequest.email(), id))
            throw new EmailAlreadyExistsException("email já cadastrado");

        if(!userUpdateRequest.name().isBlank())
            user.setName(userUpdateRequest.name());

        if(!userUpdateRequest.email().isBlank())
            user.setEmail(userUpdateRequest.email());

        userRepository.save(user);
    }

}
