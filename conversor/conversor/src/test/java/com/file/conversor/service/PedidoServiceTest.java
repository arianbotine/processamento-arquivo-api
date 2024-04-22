package com.file.conversor.service;

import com.file.conversor.repository.entity.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PedidoServiceTest {

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoDao pedidoDaoMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName(value = "Deve registrar pedido")
    public void deveRegistrarPedido() {
        when(pedidoDaoMock.criar(Pedido.builder().build())).thenReturn(Pedido.builder().build());

        Pedido pedido = pedidoService.registrar(Pedido.builder().build());
        Pedido pedidoSalvo = new Pedido();

        assertEquals(pedido, pedidoSalvo);
        verify(pedidoDaoMock).criar(Pedido.builder().build());
    }
}