package com.file.conversor.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.Date;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido")
    private List<PedidoProduto> pedidoProdutos;

    @Column(name = "valor_total")
    private Float valorTotal;
}
