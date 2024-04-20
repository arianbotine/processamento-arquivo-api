package com.file.conversor.repository.dao;

import com.file.conversor.repository.PedidoProdutoRepository;
import com.file.conversor.repository.entity.Pedido;
import com.file.conversor.repository.entity.PedidoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
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

    public List<PedidoProduto> buscar (Pedido pedido) {
        return pedidoProdutoRepository.findByPedido(pedido);
    }

}
