package com.file.conversor.mother;

import com.file.conversor.repository.entity.Pedido;
import com.file.conversor.repository.entity.Pedido.PedidoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PedidoMother {

    @Autowired
    UsuarioMother usuarioMother;

    public PedidoBuilder simples() {
        return Pedido.builder()
                .id(78L)
                .usuario(usuarioMother.simples().build())
                .dataCompra(new Date())
                .valorTotal(100.20F);
    }
}
