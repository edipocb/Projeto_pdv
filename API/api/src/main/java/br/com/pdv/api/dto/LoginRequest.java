package br.com.pdv.api.dto;

import lombok.Data;

@Data
public class LoginRequest {
    // Campo para receber o email do usuário.
    private String email;

    // Campo para receber a senha do usuário.
    private String senha;
}
