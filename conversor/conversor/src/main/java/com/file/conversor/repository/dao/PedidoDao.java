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

    public void criar(PedidoDto pedidoDto) {
        Pedido pedido;
        Optional<Pedido> pedidoOptional =
                pedidoRepository.findByCodigoAndProdutoIdAndDataCompra(
                        pedidoDto.getCodigo(),
                        pedidoDto.getProdutoId(),
                        pedidoDto.getDataCompra());
        if (pedidoOptional.isPresent()) {
            pedido = pedidoOptional.get();
            System.out.println(pedido.getCodigo() + " " + pedido.getProdutoId() + " " + pedido.getDataCompra());
            Float valor = pedido.getValor() + pedidoDto.getValor();
            pedido.setValor(valor);
        } else {
            pedido = Pedido.builder()
                    .codigo(pedidoDto.getCodigo())
                    .produtoId(pedidoDto.getProdutoId())
                    .valor(pedidoDto.getValor())
                    .dataCompra(pedidoDto.getDataCompra())
                    .usuario(pedidoDto.getUsuario())
                    .build();
        }


        pedidoRepository.save(pedido);
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
