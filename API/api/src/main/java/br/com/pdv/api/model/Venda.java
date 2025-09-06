package br.com.pdv.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "venda", schema = "pdv")
public class Venda {
    @Id
    @Column(name = "id_venda", nullable = false)
    private Integer id;

    @Column(name = "data_venda")
    private OffsetDateTime dataVenda;

    @Column(name = "valor_total", precision = 18, scale = 4)
    private BigDecimal valorTotal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente idCliente;

}