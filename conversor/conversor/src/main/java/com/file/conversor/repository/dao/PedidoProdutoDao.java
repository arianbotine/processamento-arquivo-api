package com.file.conversor.repository.dao;

import com.file.conversor.repository.PedidoProdutoRepository;
import com.file.conversor.repository.entity.PedidoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoProdutoDao {

    @Autowired
    private PedidoProdutoRepository pedidoProdutoRepository;

    public void criar(PedidoProduto pedidoProduto) {
        Optional<PedidoProduto> pedidoProdutoValorOptional =
                pedidoProdutoRepository.findByPedidoAndProduto(pedidoProduto.getPedido(), pedidoProduto.getProduto());
        if (pedidoProdutoValorOptional.isPresent()) {
            pedidoProduto = pedidoProdutoValorOptional.get();
            Float valor = pedidoProduto.getValor() + pedidoProduto.getValor();
            pedidoProduto.setValor(valor);
        }
        pedidoProdutoRepository.save(pedidoProduto);
    }

}
