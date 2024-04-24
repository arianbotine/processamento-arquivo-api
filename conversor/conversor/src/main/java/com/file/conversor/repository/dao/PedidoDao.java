package com.file.conversor.repository.dao;

import com.file.conversor.repository.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoDao extends JpaRepository<Pedido, Long> {

}
