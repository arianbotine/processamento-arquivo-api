package com.file.conversor.service;

import com.file.conversor.mother.UsuarioMother;
import com.file.conversor.repository.dao.UsuarioDao;
import com.file.conversor.repository.dto.UsuarioDto;
import com.file.conversor.repository.entity.Usuario;
import com.file.conversor.service.mapper.UsuarioDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
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

    @Mock
    UsuarioDtoMapper usuarioDtoMapper;

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

    @Test
    @DisplayName(value = "Deve retornar uma lista com todos os usuarios"
            + " e seus respectivos pedidos quando nenhum filtro for informado")
    void deveRetornarListaComTodosUsuarios() {

        List<Usuario> usuarios = List.of(
                UsuarioMother.simples().id(15L).build(),
                UsuarioMother.simples().id(16L).build(),
                UsuarioMother.simples().id(17L).build());
        Pageable pageable = Pageable.unpaged();
        Page<Usuario> page = new PageImpl<>(usuarios);

        when(usuarioDaoMock.findAll(pageable)).thenReturn(page);

        List<Usuario> usuariosRetornados = usuarioService.buscarTodos(pageable);

        assertTrue(Objects.nonNull(usuariosRetornados));
        assertEquals(usuariosRetornados.size(), 3);

        verify(usuarioDaoMock, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName(value = "Deve retornar uma lista de usuario vazia quando"
            + " filtrado por pedido e o pedido for inexistente.")
    void deveRetornarListaVaziaQuandoNenhumPedidoExistir() {
        List<Usuario> usuarios = List.of();
        Pageable pageable = Pageable.unpaged();
        Page<Usuario> page = new PageImpl<>(usuarios);

        when(usuarioDaoMock.findAll(pageable)).thenReturn(page);

        List<Usuario> pedidosRetornados = usuarioService.buscarTodos(pageable);

        assertTrue(Objects.nonNull(pedidosRetornados));
        assertEquals(pedidosRetornados.size(), 0);

        verify(usuarioDaoMock, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName(value = "Deve retornar um único usuário quando filtrado por pedido")
    void deveRetornarUmUnicoUsuarioQuandoConsultadoPorPedido() {
        Long pedidoId = 15L;
        Usuario usuario = UsuarioMother.simples().build();

        when(usuarioDaoMock.findUsuarioByPedido(pedidoId)).thenReturn(usuario);

        Usuario usuarioRetornado = usuarioService.buscarPorPedidoId(pedidoId);

        assertTrue(Objects.nonNull(usuarioRetornado));

        verify(usuarioDaoMock, times(1)).findUsuarioByPedido(pedidoId);
    }

    @Test
    @DisplayName(value = "Deve nulo quando consultado por pedido inexistente")
    void deveRetornarNuloQuandoPedidoInexistente() {
        Long pedidoId = 15L;

        when(usuarioDaoMock.findUsuarioByPedido(pedidoId)).thenReturn(null);

        Usuario usuarioRetornado = usuarioService.buscarPorPedidoId(pedidoId);

        assertTrue(Objects.isNull(usuarioRetornado));

        verify(usuarioDaoMock, times(1)).findUsuarioByPedido(pedidoId);
    }

    @Test
    @DisplayName(value = "Deve retornar lista de usuarios e seus respectivos pedidos quando filtrado por período")
    void deveRetornarListaUsuarioQuandoFiltradoPorPeriodo() {
        List<Usuario> usuarios = List.of(
                UsuarioMother.simples().id(15L).build(),
                UsuarioMother.simples().id(16L).build(),
                UsuarioMother.simples().id(17L).build());

        Date dataInicial = new Date(2024, 3, 20);
        Date dataFinal = new Date(2024, 3, 25);
        when(usuarioDaoMock.findUsuarioByDataCompraPedidoBetween(dataInicial, dataFinal)).thenReturn(usuarios);

        List<Usuario> usuariosRetornados = usuarioService.buscarPorDataCompraPedido(dataInicial, dataFinal);

        assertTrue(Objects.nonNull(usuariosRetornados));
        assertEquals(usuariosRetornados.size(), 3);

        verify(usuarioDaoMock, times(1)).findUsuarioByDataCompraPedidoBetween(
                dataInicial,
                dataFinal);
    }

    @Test
    @DisplayName(value = "Deve retornar lista de usuarios e seus respectivos pedidos quando filtrado por período e pedido")
    void deveRetornarListaUsuarioQuandoFiltradoPorPeriodoAndPedido() {
        Long pedidoId = 54L;
        List<Usuario> usuarios = List.of(
                UsuarioMother.simples().id(15L).build(),
                UsuarioMother.simples().id(16L).build(),
                UsuarioMother.simples().id(17L).build());

        Date dataInicial = new Date(2024, 3, 20);
        Date dataFinal = new Date(2024, 3, 25);
        when(usuarioDaoMock.findUsuarioByPedidoAndDataCompraPedidoBetween(pedidoId, dataInicial, dataFinal)).thenReturn(usuarios);

        List<Usuario> usuariosRetornados =
                usuarioService.buscarPorDataCompraPedidoAndPedido(pedidoId, dataInicial, dataFinal);

        assertTrue(Objects.nonNull(usuariosRetornados));
        assertEquals(usuariosRetornados.size(), 3);

        verify(usuarioDaoMock, times(1)).findUsuarioByPedidoAndDataCompraPedidoBetween(pedidoId, dataInicial, dataFinal);
    }

    @Test
    @DisplayName(value = "Deve converter uma lista da entidade de usuário em uma lista da dto de usuario")
    void deveConverterUmaListaDeUsuarioEmListaDto() {
        List<Usuario> usuarios = List.of(
                UsuarioMother.simples().id(15L).build(),
                UsuarioMother.simples().id(16L).build(),
                UsuarioMother.simples().id(17L).build());
        List<UsuarioDto> usuarioDtos = List.of(
                UsuarioDto.builder().id(15L).build(),
                UsuarioDto.builder().id(16L).build(),
                UsuarioDto.builder().id(17L).build());

        when(usuarioDtoMapper.toUsuarioDtoList(usuarios)).thenReturn(usuarioDtos);

        List<UsuarioDto> usuariosRetornados =
                usuarioService.toListDto(usuarios);

        assertTrue(Objects.nonNull(usuariosRetornados));
        assertEquals(usuariosRetornados.size(), 3);

        verify(usuarioDtoMapper, times(1)).toUsuarioDtoList(usuarios);
    }

}