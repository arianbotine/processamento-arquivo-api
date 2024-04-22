package com.file.conversor.mother;

import com.file.conversor.repository.entity.Usuario;
import com.file.conversor.repository.entity.Usuario.UsuarioBuilder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioMother {

    public UsuarioBuilder simples() {
        return Usuario.builder()
                .id(15L)
                .nome("Bobbie Batz");
    }
}
