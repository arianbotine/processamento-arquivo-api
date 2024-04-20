package com.file.conversor.repository.dao;

import com.file.conversor.repository.UsuarioRepository;
import com.file.conversor.repository.dto.UsuarioDto;
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
        Optional<Usuario> usuarioOptional  = usuarioRepository.findById(usuarioDto.getId());
        if (usuarioOptional.isPresent()) {
            usuario = usuarioOptional.get();
            if (!usuario.getNome().equals(usuarioDto.getNome())) {
                return usuario;
            }
            usuario.setNome(usuarioDto.getNome());
        } else {
            usuario = Usuario.builder()
                    .id(usuarioDto.getId())
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
