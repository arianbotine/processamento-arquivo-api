package com.file.conversor.mother;

import com.file.conversor.repository.entity.Pedido;
import com.file.conversor.repository.entity.Pedido.PedidoBuilder;

import java.util.Date;
import java.util.List;

public class PedidoMother {

    public static PedidoBuilder simples() {
        return Pedido.builder()
                .id(78L)
                .usuario(UsuarioMother.simples().build())
                .dataCompra(new Date(2024, 3, 15))
                .valorTotal(100.20F);
    }

    public static PedidoBuilder comUmProduto() {
        return Pedido.builder()
                .id(78L)
                .usuario(UsuarioMother.simples().build())
                .dataCompra(new Date(2024, 3, 15))
                .valorTotal(100.20F)
                .pedidoProdutos(List.of(PedidoProdutoMother.simples().build()));
    }
}
