package com.file.conversor.service;

import com.file.conversor.repository.dao.PedidoProdutoDao;
import com.file.conversor.repository.entity.PedidoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoProdutoService {

    @Autowired
    PedidoProdutoDao pedidoProdutoDao;

    public void registrar (PedidoProduto pedidoProduto) {

        Optional<PedidoProduto> pedidoProdutoValorOptional =
                pedidoProdutoDao.findByPedidoAndProduto(
                        pedidoProduto.getPedido(),
                        pedidoProduto.getProduto());
        if (pedidoProdutoValorOptional.isPresent()) {
            PedidoProduto currentPedidoProduto = pedidoProdutoValorOptional.get();
            Float valor = currentPedidoProduto.getValor() + pedidoProduto.getValor();
            currentPedidoProduto.setValor(valor);
            pedidoProduto = currentPedidoProduto;
        }

        pedidoProdutoDao.save(pedidoProduto);
    }
}
