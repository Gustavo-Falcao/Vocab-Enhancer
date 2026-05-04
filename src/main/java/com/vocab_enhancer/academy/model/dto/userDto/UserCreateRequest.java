package com.vocab_enhancer.academy.model.dto.userDto;

public record UserCreateRequest (
        String name,
        String email,
        String password
)
{
}
