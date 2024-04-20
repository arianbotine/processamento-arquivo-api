package com.file.conversor.repository.dao;

import com.file.conversor.repository.UsuarioRepository;
import com.file.conversor.repository.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioDao {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criar(Usuario newUsuario) {
        Optional<Usuario> usuarioOptional  = usuarioRepository.findById(newUsuario.getId());
        if (usuarioOptional.isPresent()) {
            Usuario currentUsuario = usuarioOptional.get();
            if (!currentUsuario.getNome().equals(newUsuario.getNome())) {
                return currentUsuario;
            }
            newUsuario.setNome(newUsuario.getNome());
        } else {
            newUsuario = Usuario.builder()
                    .id(newUsuario.getId())
                    .nome(newUsuario.getNome())
                    .build();
        }
        usuarioRepository.save(newUsuario);
        return newUsuario;
    }

}
