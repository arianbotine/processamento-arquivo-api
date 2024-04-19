package com.file.conversor.repository;

import com.file.conversor.repository.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findByCodigoAndProdutoId(Long codigo, Long ProdutoId);
}
