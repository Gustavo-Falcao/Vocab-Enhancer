package com.vocab_enhancer.academy.model.dto.userDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AuthenticationDTO(
        @NotBlank(message = "Email é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)\\S{8,50}$",
                message = "Senha deve conter pelo menos 1 letra minúscula, 1 letra maiúscula, 1 dígito e ter entre 8 e 50 caracteres, sem espaços em branco."
        )
        String password
) {
}
