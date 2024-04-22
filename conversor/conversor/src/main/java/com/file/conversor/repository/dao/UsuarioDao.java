package com.file.conversor.repository.dao;

import com.file.conversor.repository.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long> {

    @Query("SELECT DISTINCT usuario FROM Usuario usuario JOIN usuario.pedidos p WHERE p.id = :pedidoId")
    Usuario findUsuarioByPedido(Long pedidoId);

    @Query("SELECT DISTINCT usuario FROM Usuario usuario JOIN FETCH usuario.pedidos p WHERE p.dataCompra BETWEEN :dataInicial AND :dataFinal")
    List<Usuario> findUsuarioByDataCompraPedidoBetween(Date dataInicial, Date dataFinal);

    @Query("SELECT DISTINCT usuario FROM Usuario usuario JOIN FETCH usuario.pedidos p WHERE p.id = :pedidoId AND p.dataCompra BETWEEN :dataInicial AND :dataFinal")
    List<Usuario> findUsuarioByPedidoAndDataCompraPedidoBetween(Long pedidoId, Date dataInicial, Date dataFinal);
}
