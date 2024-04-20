package com.file.conversor.services;

import com.file.conversor.repository.dao.PedidoProdutoDao;
import com.file.conversor.repository.dto.PedidoProdutoDto;
import com.file.conversor.repository.entity.Pedido;
import com.file.conversor.repository.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoProdutoService {

    @Autowired
    PedidoProdutoDao pedidoProdutoDao;

    public void registrar (Pedido pedido, Produto produto, Float valorTotal) {
        pedidoProdutoDao.criar(PedidoProdutoDto.builder().pedido(pedido).produto(produto).valor(valorTotal).build());
    }
}
