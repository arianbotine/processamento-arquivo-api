package com.file.conversor.repository.dao;

import com.file.conversor.repository.PedidoRepository;
import com.file.conversor.repository.UsuarioRepository;
import com.file.conversor.repository.dto.PedidoDto;
import com.file.conversor.repository.dto.UsuarioDto;
import com.file.conversor.repository.entity.Pedido;
import com.file.conversor.repository.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioDao {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criar(UsuarioDto usuarioDto) {
        Usuario usuario;
        Optional<Usuario> usuarioOptional  = usuarioRepository.findByCodigo(usuarioDto.getCodigo());
        if (usuarioOptional.isPresent()) {
            usuario = usuarioOptional.get();
            usuario.setNome(usuarioDto.getNome());
        } else {
            usuario = Usuario.builder()
                    .codigo(usuarioDto.getCodigo())
                    .nome(usuarioDto.getNome())
                    .build();
        }
        usuarioRepository.save(usuario);
        return usuario;
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
