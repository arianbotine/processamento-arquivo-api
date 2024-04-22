package com.file.conversor.service;

import com.file.conversor.repository.dao.UsuarioDao;
import com.file.conversor.repository.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioDao usuarioDao;

    public Usuario registrar (String registro) {
        String usuarioId = registro.substring(1,10);
        String nome = registro.substring(11,55).trim();

        Long usuarioIdint = converterStringParaLong(usuarioId);
        return usuarioDao.criar(Usuario.builder()
                .id(usuarioIdint)
                .nome(nome)
                .build());
    }

    public Long converterStringParaLong(String string) {
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("Falha ao converter em número o registro: " + string);
        }
    }
}
