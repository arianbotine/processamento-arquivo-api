package com.file.conversor.repository.dao;

import com.file.conversor.repository.PedidoRepository;
import com.file.conversor.repository.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoDao {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido criar(Pedido newPedido) {
        Optional<Pedido> pedidoOptional =
                pedidoRepository.findById(newPedido.getId());
        if (pedidoOptional.isPresent()) {
            Pedido currentPedido = pedidoOptional.get();
            Float valorTotal = currentPedido.getValorTotal() + newPedido.getValorTotal();
            newPedido.setValorTotal(valorTotal);
        }

        pedidoRepository.save(newPedido);
        return newPedido;
    }

    public List<Pedido> buscarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long pedidoId) {
        return pedidoRepository.findById(pedidoId);
    }

    public List<Pedido> buscarPorDataCompra(Date dataInicial, Date dataFinal) {
        return pedidoRepository.findByDataCompraBetween(dataInicial,dataFinal);
    }

}
