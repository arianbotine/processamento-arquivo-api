package com.file.conversor.service;

import com.file.conversor.mother.UsuarioMother;
import com.file.conversor.repository.dao.UsuarioDao;
import com.file.conversor.repository.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Autowired
    @InjectMocks
    UsuarioService usuarioService;

    @Mock
    UsuarioDao usuarioDaoMock;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName(value = "Deve registrar um novo usuario")
    void deveRegistrarNovoUsuario() {
        Usuario usuario = UsuarioMother.simples().build();

        when(usuarioDaoMock.findById(usuario.getId())).thenReturn(Optional.empty());
        when(usuarioDaoMock.save(usuario)).thenReturn(usuario);

        Usuario usuarioSalvo = usuarioService.registrar(usuario);

        assertTrue(Objects.nonNull(usuarioSalvo));

        verify(usuarioDaoMock).findById(usuario.getId());
        verify(usuarioDaoMock).save(usuario);
    }

    @Test
    @DisplayName(value = "Deve atualizar o nome de um usuário existente")
    void deveAtualizarNomeUsuarioExistente() {
        String nomeNovo = "Bobbie Batz";
        String nomeAtual = "Augustus Aufderha";
        Usuario usuarioNomeNovo = UsuarioMother.simples().nome(nomeNovo).build();
        Usuario usuarioNomeAtual = UsuarioMother.simples().nome(nomeAtual).build();


        when(usuarioDaoMock.findById(usuarioNomeNovo.getId())).thenReturn(Optional.of(usuarioNomeAtual));
        when(usuarioDaoMock.save(usuarioNomeAtual)).thenReturn(usuarioNomeNovo);

        Usuario usuarioSalvo = usuarioService.registrar(usuarioNomeNovo);

        assertTrue(Objects.nonNull(usuarioSalvo));
        assertEquals(usuarioSalvo.getNome(), usuarioNomeNovo.getNome());

        verify(usuarioDaoMock).findById(usuarioNomeNovo.getId());
        verify(usuarioDaoMock).save(usuarioNomeAtual);
    }

    @Test
    @DisplayName(value = "Não deve persistir quando usuário já existir com o mesmo nome")
    void naoDevePersistirQuandoUsuarioJaExisteComMesmoNome() {
        Usuario usuario = UsuarioMother.simples().build();

        when(usuarioDaoMock.findById(usuario.getId())).thenReturn(Optional.of(usuario));

        Usuario usuarioSalvo = usuarioService.registrar(usuario);

        assertTrue(Objects.nonNull(usuarioSalvo));

        verify(usuarioDaoMock).findById(usuario.getId());
        verify(usuarioDaoMock, never()).save(usuario);
    }
}