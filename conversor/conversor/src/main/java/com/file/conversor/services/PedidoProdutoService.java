package com.file.conversor.services;

import com.file.conversor.repository.dao.PedidoProdutoDao;
import com.file.conversor.repository.entity.Pedido;
import com.file.conversor.repository.entity.PedidoProduto;
import com.file.conversor.repository.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoProdutoService {

    @Autowired
    PedidoProdutoDao pedidoProdutoDao;

    @Autowired
    PedidoService pedidoService;

    public void registrar (Pedido pedido, Produto produto, Float valorTotal) {
        pedidoProdutoDao.criar(PedidoProduto.builder()
                .pedido(pedido)
                .produto(produto)
                .valor(valorTotal)
                .build());
    }

    public List<PedidoProduto> buscar (Long pedidoId) {
        Pedido pedido = pedidoService.buscar(pedidoId);
        return pedidoProdutoDao.buscar(pedido);
    }
}
