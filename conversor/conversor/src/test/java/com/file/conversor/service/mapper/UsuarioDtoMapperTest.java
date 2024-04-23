package com.file.conversor.service.mapper;

import com.file.conversor.mother.UsuarioMother;
import com.file.conversor.repository.dto.UsuarioDto;
import com.file.conversor.repository.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsuarioDtoMapperTest {
    @Autowired
    @InjectMocks
    UsuarioDtoMapper usuarioDtoMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName(value = "Deve converter uma lista de entidade de usuário para uma lista de usuario DTO")
    void deveConverterListaEntidadeUsuarioEmListaUsuarioDto() {
        List<Usuario> usuarios = List.of(
                UsuarioMother.comUmPedido().id(15L).build(),
                UsuarioMother.comUmPedido().id(16L).build(),
                UsuarioMother.comUmPedido().id(17L).build());

        List<UsuarioDto> usuariosDto = usuarioDtoMapper.toUsuarioDtoList(usuarios);

        assertEquals(usuariosDto.size(), 3);
    }

}