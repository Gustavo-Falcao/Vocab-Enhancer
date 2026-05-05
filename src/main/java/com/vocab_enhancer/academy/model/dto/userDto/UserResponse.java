package com.vocab_enhancer.academy.model.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private long id;
    private String nome;
    private String email;
}
