package org.example.cati.infra.security.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {

    @NotBlank(message = "LOGIN não pode ser nulo!")
    private String login;
    @NotBlank(message = "SENHA não pode ser nula!")
    private String senha;
}
