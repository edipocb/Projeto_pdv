package br.com.pdv.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "item_venda", schema = "pdv")
public class ItemVenda {
    @Id
    @Column(name = "id_item_venda", nullable = false)
    private Integer id;

    @Column(name = "quantidade_item", nullable = false)
    private Integer quantidadeItem;

    @Column(name = "preco_unitario", precision = 18, scale = 4)
    private BigDecimal precoUnitario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_venda", nullable = false)
    private Venda idVenda;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto idProduto;

}