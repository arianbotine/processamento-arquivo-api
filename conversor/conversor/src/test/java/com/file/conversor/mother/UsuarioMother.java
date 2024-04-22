package com.file.conversor.mother;

import com.file.conversor.repository.entity.Usuario;
import com.file.conversor.repository.entity.Usuario.UsuarioBuilder;

import java.util.List;

public class UsuarioMother {

    public static UsuarioBuilder simples() {
        return Usuario.builder()
                .id(15L)
                .nome("Bobbie Batz");
    }
}
