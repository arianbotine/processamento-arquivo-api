package com.file.conversor.repository.dao;

import com.file.conversor.repository.PedidoRepository;
import com.file.conversor.repository.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoDao {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido criar(Pedido pedido) {
        Optional<Pedido> pedidoOptional =
                pedidoRepository.findById(pedido.getId());
        if (pedidoOptional.isPresent()) {
            pedido = pedidoOptional.get();
            Float valorTotal = pedido.getValorTotal() + pedido.getValorTotal();
            pedido.setValorTotal(valorTotal);
        }

        pedidoRepository.save(pedido);
        return pedido;
    }

    public Optional<Pedido> findById(Long pedidoId) {
        return pedidoRepository.findById(pedidoId);
    }

}
