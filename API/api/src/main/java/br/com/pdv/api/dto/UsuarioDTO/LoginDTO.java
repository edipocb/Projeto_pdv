package br.com.pdv.api.dto.UsuarioDTO;

import lombok.Data;

@Data
public class LoginDTO {
    private String token;
    private ListarClientesDTO Cliente;

}
