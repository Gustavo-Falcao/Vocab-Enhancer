package com.vocab_enhancer.academy.model.dto.userDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
        @Size(min = 3, max = 100, message = ("Nome deve ter entre 3 a 100 letras"))
        String name,

        @Email(message = "E-mail inválido")
        String email
) {
}
