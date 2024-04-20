package com.file.conversor.repository.dao;

import com.file.conversor.repository.PedidoProdutoRepository;
import com.file.conversor.repository.dto.PedidoProdutoDto;
import com.file.conversor.repository.entity.PedidoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoProdutoDao {

    @Autowired
    private PedidoProdutoRepository pedidoProdutoRepository;

    public void criar(PedidoProdutoDto pedidoProdutoDto) {
        PedidoProduto pedidoProduto;
        Optional<PedidoProduto> pedidoProdutoValorOptional =
                pedidoProdutoRepository.findByPedidoAndProduto(pedidoProdutoDto.getPedido(), pedidoProdutoDto.getProduto());
        if (pedidoProdutoValorOptional.isPresent()) {
            pedidoProduto = pedidoProdutoValorOptional.get();
            Float valor = pedidoProduto.getValor() + pedidoProdutoDto.getValor();
            pedidoProduto.setValor(valor);
        } else {
            pedidoProduto = PedidoProduto.builder()
                    .pedido(pedidoProdutoDto.getPedido())
                    .produto(pedidoProdutoDto.getProduto())
                    .valor(pedidoProdutoDto.getValor())
                    .build();
        }

        pedidoProdutoRepository.save(pedidoProduto);
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
