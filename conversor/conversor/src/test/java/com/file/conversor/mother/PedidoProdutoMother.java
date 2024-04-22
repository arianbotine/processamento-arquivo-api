package com.file.conversor.mother;

import com.file.conversor.repository.entity.PedidoProduto;
import com.file.conversor.repository.entity.PedidoProduto.PedidoProdutoBuilder;

public class PedidoProdutoMother {

    public static PedidoProdutoBuilder semPersistir() {
        return PedidoProduto.builder()
                .pedido(PedidoMother.simples().build())
                .produto(ProdutoMother.simples().build());
    }
}
