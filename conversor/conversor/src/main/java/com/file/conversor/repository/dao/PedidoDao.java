package com.file.conversor.repository.dao;

import com.file.conversor.repository.PedidoRepository;
import com.file.conversor.repository.dto.PedidoDto;
import com.file.conversor.repository.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoDao {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido criar(PedidoDto pedidoDto) {
        Pedido pedido;
        Optional<Pedido> pedidoOptional =
                pedidoRepository.findById(pedidoDto.getId());
        if (pedidoOptional.isPresent()) {
            pedido = pedidoOptional.get();
            Float valorTotal = pedido.getValorTotal() + pedidoDto.getValorTotal();
            pedido.setValorTotal(valorTotal);
        } else {
            pedido = Pedido.builder()
                    .id(pedidoDto.getId())
                    .dataCompra(pedidoDto.getDataCompra())
                    .usuario(pedidoDto.getUsuario())
                    .valorTotal(pedidoDto.getValorTotal())
                    .build();
        }

        pedidoRepository.save(pedido);
        return pedido;
    }
/*
    public TarefaDto atualizar (TarefaDto TarefaDto, Long tarefaId) {
        Tarefa tarefaDatabase = tarefaRepositoryInterface.getReferenceById(tarefaId);
        tarefaDatabase.setTitulo(TarefaDto.getTitulo());
        tarefaDatabase.setDescricao(TarefaDto.getTitulo());
        tarefaRepositoryInterface.save(tarefaDatabase);
        return TarefaDto;
    }

    private TarefaDto converter (Tarefa tarefa) {
        TarefaDto result = new TarefaDto();
        result.setId(tarefa.getId());
        result.setTitulo(tarefa.getTitulo());
        result.setDescricao(tarefa.getDescricao());
        return result;
    }

    public List<TarefaDto> getAll () {
        return tarefaRepositoryInterface
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }
    */

}
