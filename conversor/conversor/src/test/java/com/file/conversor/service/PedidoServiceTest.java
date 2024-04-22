package com.file.conversor.service;

import com.file.conversor.mother.PedidoMother;
import com.file.conversor.repository.dao.PedidoDao;
import com.file.conversor.repository.entity.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class PedidoServiceTest {

    @Autowired
    @InjectMocks
    PedidoService pedidoService;

    @Mock
    PedidoDao pedidoDaoMock;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName(value = "Deve registrar um novo pedido")
    void deveRegistrarNovoPedido() {
        Pedido pedido = PedidoMother.simples().build();

        when(pedidoDaoMock.findById(pedido.getId())).thenReturn(Optional.empty());
        when(pedidoDaoMock.save(pedido)).thenReturn(pedido);

        Pedido pedidoSalvo = pedidoService.registrar(pedido);

        assertTrue(Objects.nonNull(pedidoSalvo));

        verify(pedidoDaoMock).findById(pedido.getId());
        verify(pedidoDaoMock).save(pedido);
    }


    @Test
    @DisplayName(value = "Deve atualizar o valor total do pedido caso já exista")
    void deveAtualizarValorTotalPedidoCasoJaExista() {
        Pedido pedidoNovo = PedidoMother.simples().valorTotal(100F).build();
        Pedido pedidoAtual = PedidoMother.simples().valorTotal(200F).build();
        Float valorTotal = pedidoNovo.getValorTotal() + pedidoAtual.getValorTotal();
        Pedido pedidoFinal = PedidoMother.simples().valorTotal(valorTotal).build();

        when(pedidoDaoMock.findById(pedidoNovo.getId())).thenReturn(Optional.of(pedidoAtual));
        when(pedidoDaoMock.save(pedidoAtual)).thenReturn(pedidoFinal);

        Pedido pedidoSalvo = pedidoService.registrar(pedidoNovo);

        assertTrue(Objects.nonNull(pedidoSalvo));
        assertEquals(pedidoSalvo.getValorTotal(), pedidoFinal.getValorTotal());

        verify(pedidoDaoMock).findById(pedidoNovo.getId());
        verify(pedidoDaoMock).save(any(Pedido.class));
    }

    @Test
    @DisplayName(value = "Deve retornar uma lista de pedidos quando nenhum filtro informado")
    void deveRetornarListaPedidoQuandoNenhumFiltroInformado() {
        List<Pedido> pedidos = List.of(
                PedidoMother.simples().id(15L).build(),
                PedidoMother.simples().id(16L).build(),
                PedidoMother.simples().id(17L).build());

        when(pedidoDaoMock.findAll()).thenReturn(pedidos);

        List<Pedido> pedidosRetornados = pedidoService.buscarTodos();

        assertTrue(Objects.nonNull(pedidosRetornados));
        assertEquals(pedidosRetornados.size(), 3);

        verify(pedidoDaoMock, times(1)).findAll();
    }

    @Test
    @DisplayName(value = "Deve retornar uma lista vazia quando nenhum pedido existir")
    void deveRetornarListaVaziaQuandoNenhumPedidoExistir() {
        List<Pedido> pedidos = List.of();

        when(pedidoDaoMock.findAll()).thenReturn(pedidos);

        List<Pedido> pedidosRetornados = pedidoService.buscarTodos();

        assertTrue(Objects.nonNull(pedidosRetornados));
        assertEquals(pedidosRetornados.size(), 0);

        verify(pedidoDaoMock, times(1)).findAll();
    }

    @Test
    @DisplayName(value = "Deve retornar um único pedido quando consultado por código")
    void deveRetornarUmUnicoPedidoQuandoConsultadoPorCodigo() {
        Pedido pedido = PedidoMother.simples().build();

        when(pedidoDaoMock.findById(pedido.getId())).thenReturn(Optional.of(pedido));

        Pedido pedidoRetornado = pedidoService.buscarPorId(pedido.getId());

        assertTrue(Objects.nonNull(pedidoRetornado));

        verify(pedidoDaoMock, times(1)).findById(pedido.getId());
    }

    @Test
    @DisplayName(value = "Deve nulo quando consultado por código e registro não existir")
    void deveRetornarNuloQuandoRegistroInexistente() {
        Long pedidoId = 15L;

        when(pedidoDaoMock.findById(pedidoId)).thenReturn(Optional.empty());

        Pedido pedidoRetornado = pedidoService.buscarPorId(pedidoId);

        assertTrue(Objects.isNull(pedidoRetornado));

        verify(pedidoDaoMock, times(1)).findById(pedidoId);
    }

    @Test
    @DisplayName(value = "Deve retornar lista de pedidos quando filtrado por período")
    void deveRetornarListaPedidoQuandoFiltradoPorPeriodo() {
        List<Pedido> pedidos = List.of(
                PedidoMother.simples().id(15L).build(),
                PedidoMother.simples().id(16L).build(),
                PedidoMother.simples().id(17L).build());

        Date dataInicial = new Date(2024, 3, 20);
        Date dataFinal = new Date(2024, 3, 25);
        when(pedidoDaoMock.findByDataCompraBetween(dataInicial, dataFinal)).thenReturn(pedidos);

        List<Pedido> pedidosRetornados = pedidoService.buscarPorDataCompra(dataInicial, dataFinal);

        assertTrue(Objects.nonNull(pedidosRetornados));
        assertEquals(pedidosRetornados.size(), 3);

        verify(pedidoDaoMock, times(1)).findByDataCompraBetween(dataInicial, dataFinal);
    }

}