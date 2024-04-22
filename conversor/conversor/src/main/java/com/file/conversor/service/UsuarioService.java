package com.file.conversor.service;

import com.file.conversor.repository.dao.UsuarioDao;
import com.file.conversor.repository.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioDao usuarioDao;

    public Usuario registrar (Usuario usuario) {

        Optional<Usuario> usuarioOptional = usuarioDao.findById(usuario.getId());
        if (usuarioOptional.isPresent()) {
            Usuario currentUsuario = usuarioOptional.get();
            if (currentUsuario.getNome().equals(usuario.getNome())) {
                return currentUsuario;
            }
            currentUsuario.setNome(usuario.getNome());
            usuario = currentUsuario;
        }
        return usuarioDao.save(usuario);
    }
}
