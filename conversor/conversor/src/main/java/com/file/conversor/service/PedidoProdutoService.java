package com.file.conversor.service;

import com.file.conversor.repository.dao.PedidoProdutoDao;
import com.file.conversor.repository.entity.Pedido;
import com.file.conversor.repository.entity.PedidoProduto;
import com.file.conversor.repository.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoProdutoService {

    @Autowired
    PedidoProdutoDao pedidoProdutoDao;

    public void registrar (Pedido pedido, Produto produto, Float produtoValor) {

        PedidoProduto pedidoProduto =
                PedidoProduto.builder()
                        .pedido(pedido)
                        .produto(produto)
                        .valor(produtoValor)
                        .build();

        Optional<PedidoProduto> pedidoProdutoValorOptional =
                pedidoProdutoDao.findByPedidoAndProduto(pedido, produto);
        if (pedidoProdutoValorOptional.isPresent()) {
            pedidoProduto = pedidoProdutoValorOptional.get();
            Float valor = pedidoProduto.getValor() + produtoValor;
            pedidoProduto.setValor(valor);
        }

        pedidoProdutoDao.save(pedidoProduto);
    }
}
