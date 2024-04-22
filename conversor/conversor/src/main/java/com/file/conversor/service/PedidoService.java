package com.file.conversor.service;

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

        Optional<Pedido> pedidoOptional =
                pedidoDao.findById(pedido.getId());
        if (pedidoOptional.isPresent()) {
            Pedido currentPedido = pedidoOptional.get();
            Float valorTotal = currentPedido.getValorTotal() + pedido.getValorTotal();
            currentPedido.setValorTotal(valorTotal);
            pedido = currentPedido;
        }

        return pedidoDao.save(pedido);
    }

}
