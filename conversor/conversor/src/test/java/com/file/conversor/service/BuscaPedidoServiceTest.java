package com.file.conversor.service;

import com.file.conversor.mother.PedidoMother;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BuscaPedidoServiceTest {

    @Autowired
    @InjectMocks
    BuscaPedidoService buscaPedidoService;

    @Mock
    PedidoService pedidoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName(value = "Deve retornar uma lista com todos os usuários quando nenhum parâmetro for informado")
    void deveRetornarListaTodosUsuarios() {
        /*
        Pedido pedido = PedidoMother.simples().build();

        when(pedidoDaoMock.findById(pedido.getId())).thenReturn(Optional.empty());
        when(pedidoDaoMock.save(pedido)).thenReturn(pedido);

        Pedido pedidoSalvo = pedidoService.registrar(pedido);

        assertTrue(Objects.nonNull(pedidoSalvo));

        verify(pedidoDaoMock).findById(pedido.getId());
        verify(pedidoDaoMock).save(pedido);
         */
    }
}