package br.com.pdv.api.dto.UsuarioDTO;

import lombok.Data;

@Data
public class CadastrarClienteDTO {
    private String email;
    private String senha;
    private Integer cpf;
    private String nome;
    private String telefone;
}
