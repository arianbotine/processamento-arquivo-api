package com.file.conversor.mother;

import com.file.conversor.repository.entity.Pedido;
import com.file.conversor.repository.entity.Pedido.PedidoBuilder;

import java.util.Date;

public class PedidoMother {

    public static PedidoBuilder simples() {
        return Pedido.builder()
                .id(78L)
                .usuario(UsuarioMother.simples().build())
                .dataCompra(new Date())
                .valorTotal(100.20F);
    }
}
