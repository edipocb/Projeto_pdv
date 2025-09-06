package br.com.pdv.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente", schema = "pdv")
public class Cliente {
    @Id
    @Column(name = "id_cliente", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = Integer.MAX_VALUE)
    private String nome;

    @Column(name = "cpf", nullable = false)
    private Integer cpf;

    @Column(name = "telefone", nullable = false, length = Integer.MAX_VALUE)
    private String telefone;

    @Column(name = "email", nullable = false, length = Integer.MAX_VALUE)
    private String email;

}