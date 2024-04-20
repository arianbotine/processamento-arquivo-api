package com.file.conversor.services;

import com.file.conversor.repository.dao.PedidoDao;
import com.file.conversor.repository.entity.Pedido;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoDao pedidoDao;

    @Transactional
    public Pedido registrar(Pedido pedido) {
        return pedidoDao.criar(pedido);
    }

    public Pedido buscar (Long pedidoId) {
        Optional<Pedido> pedidoOptional = pedidoDao.findById(pedidoId);
        return pedidoOptional.orElse(null);
    }

}
