package com.file.conversor.repository.dao;

import com.file.conversor.repository.ProdutoRepository;
import com.file.conversor.repository.dto.ProdutoDto;
import com.file.conversor.repository.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoDao {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto criar(ProdutoDto produtoDto) {
        Produto produto;
        Optional<Produto> produtoOptional  = produtoRepository.findById(produtoDto.getId());
        if (produtoOptional.isPresent()) {
            return produtoOptional.get();
        } else {
            produto = Produto.builder()
                    .id(produtoDto.getId())
                    .build();
        }
        produtoRepository.save(produto);
        return produto;
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
