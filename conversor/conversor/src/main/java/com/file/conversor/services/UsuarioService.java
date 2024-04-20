package com.file.conversor.services;

import com.file.conversor.repository.dao.UsuarioDao;
import com.file.conversor.repository.dto.UsuarioDto;
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

        Long usuarioIdint = converterStringParaInteger(usuarioId);
        return usuarioDao.criar(UsuarioDto.builder().id(usuarioIdint).nome(nome).build());
    }

    public Long converterStringParaInteger(String string) {
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("Falha ao converter em número o registro: " + string);
        }
    }
}
