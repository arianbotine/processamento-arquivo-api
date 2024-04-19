package com.file.conversor.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name ="pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name = "codigo_pedido")
    private Long codigo;

    @Column(name = "id_produto")
    private Long produtoId;

    @Column(name = "valor")
    private Float valor;

    @Column(name = "data_compra")
    private Date dataCompra;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;
}
