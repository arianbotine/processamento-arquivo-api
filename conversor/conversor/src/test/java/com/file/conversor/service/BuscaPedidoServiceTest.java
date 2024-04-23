package com.file.conversor.service;

import com.file.conversor.mother.BuscaPedidoRequestDtoMother;
import com.file.conversor.mother.UsuarioMother;
import com.file.conversor.repository.dto.BuscaPedidoRequestDto;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BuscaPedidoServiceTest {

    @Autowired
    @InjectMocks
    BuscaPedidoService buscaPedidoService;

    @Autowired
    @InjectMocks
    UsuarioDtoMapper usuarioDtoMapper;

    @Mock
    UsuarioService usuarioServiceMock;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName(value = "Deve retornar uma lista com todos os usuários quando nenhum parâmetro for informado")
    void deveRetornarListaTodosUsuarios() throws ParseException {

        List<Usuario> usuarios = List.of(
                UsuarioMother.comUmPedido().id(15L).build(),
                UsuarioMother.comUmPedido().id(16L).build(),
                UsuarioMother.comUmPedido().id(17L).build());
        List<UsuarioDto> usuariosDto = usuarioDtoMapper.toUsuarioDtoList(usuarios);

        BuscaPedidoRequestDto vazio = BuscaPedidoRequestDto.builder().build();
        Pageable pageable = PageRequest.of(
                vazio.getPage(),
                vazio.getSize());

        when(usuarioServiceMock.buscarTodos(pageable)).thenReturn(usuarios);
        when(usuarioServiceMock.toListDto(usuarios)).thenReturn(usuariosDto);

        List<UsuarioDto> usuariosDtoRetornados = buscaPedidoService.buscar(vazio);

        assertTrue(Objects.nonNull(usuariosDtoRetornados));
        assertTrue(usuariosDtoRetornados.size() > 1);
        assertEquals(usuariosDtoRetornados.size(), usuarios.size());

        verify(usuarioServiceMock).buscarTodos(pageable);
        verify(usuarioServiceMock).toListDto(usuarios);
    }

    @Test
    @DisplayName(value = "Deve retornar usuarios com seus respectivos pedidos"
            + " respeitando os filtros de pedido e data de compra")
    void deveRetornarUsuariosFiltrandoPorPedidoAndDataCompra() throws ParseException {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        List<Usuario> usuarios = List.of(
                UsuarioMother.comUmPedido().id(15L).build(),
                UsuarioMother.comUmPedido().id(16L).build(),
                UsuarioMother.comUmPedido().id(17L).build());
        List<UsuarioDto> usuariosDto = usuarioDtoMapper.toUsuarioDtoList(usuarios);

        BuscaPedidoRequestDto pedidoAndDataCompra = BuscaPedidoRequestDtoMother
                .porPedidoAndDataCompra()
                .build();
        Date dataInicial = formato.parse(pedidoAndDataCompra.getDataInicial());
        Date dataFinal = formato.parse(pedidoAndDataCompra.getDataFinal());
        when(usuarioServiceMock.buscarPorDataCompraPedidoAndPedido(
                pedidoAndDataCompra.getPedidoId(),
                dataInicial,
                dataFinal)).thenReturn(usuarios);
        when(usuarioServiceMock.toListDto(usuarios)).thenReturn(usuariosDto);

        List<UsuarioDto> usuariosDtoRetornados = buscaPedidoService.buscar(pedidoAndDataCompra);

        assertTrue(Objects.nonNull(usuariosDtoRetornados));
        assertTrue(usuariosDtoRetornados.size() > 1);
        assertEquals(usuariosDtoRetornados.size(), usuarios.size());

        verify(usuarioServiceMock).buscarPorDataCompraPedidoAndPedido(
                pedidoAndDataCompra.getPedidoId(),
                dataInicial,
                dataFinal);
        verify(usuarioServiceMock).toListDto(usuarios);
    }
}