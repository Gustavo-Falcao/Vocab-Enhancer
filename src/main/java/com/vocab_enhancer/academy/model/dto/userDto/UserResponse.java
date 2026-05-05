package com.vocab_enhancer.academy.model.dto.userDto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserResponse {
    private long id;
    private String name;
    private String email;
}
