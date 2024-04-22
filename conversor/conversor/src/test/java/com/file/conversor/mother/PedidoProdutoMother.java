package com.file.conversor.mother;

import com.file.conversor.repository.entity.PedidoProduto;
import com.file.conversor.repository.entity.PedidoProduto.PedidoProdutoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoProdutoMother {

    @Autowired
    PedidoMother pedidoMother;

    @Autowired
    ProdutoMother produtoMother;

    public PedidoProdutoBuilder semPersistir() {
        return PedidoProduto.builder()
                .pedido(pedidoMother.simples().build())
                .produto(produtoMother.simples().build());
    }
}
