package com.file.conversor.repository;

import com.file.conversor.repository.entity.Pedido;
import com.file.conversor.repository.entity.PedidoProduto;
import com.file.conversor.repository.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, Long> {

    Optional<PedidoProduto> findByPedidoAndProduto(Pedido pedido, Produto produto);

    List<PedidoProduto> findByPedido(Pedido pedido);
}
