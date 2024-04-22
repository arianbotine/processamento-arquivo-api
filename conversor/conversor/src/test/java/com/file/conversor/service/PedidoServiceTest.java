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

}