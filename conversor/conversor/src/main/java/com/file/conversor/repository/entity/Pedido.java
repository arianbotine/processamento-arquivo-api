package com.file.conversor.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name ="pedido")
public class Pedido {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "data_compra")
    private Date dataCompra;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @Column(name = "valor_total")
    private Float valorTotal;
}
