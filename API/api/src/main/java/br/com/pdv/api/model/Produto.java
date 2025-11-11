package br.com.pdv.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "produto", schema = "pdv")
public class Produto {
    @Id
    @Column(name = "id_produto", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, length = Integer.MAX_VALUE)
    private String nome;

    @Column(name = "preco", precision = 18, scale = 4)
    private BigDecimal preco;

    @Column(name = "estoque", nullable = false)
    private Integer estoque;

}