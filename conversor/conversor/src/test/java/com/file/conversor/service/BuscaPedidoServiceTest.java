package com.file.conversor.service;
import com.file.conversor.repository.dto.BuscaPedidoRequestDto;
import com.file.conversor.repository.dto.PedidoDto;
import com.file.conversor.repository.dto.ProdutoDto;
import com.file.conversor.repository.dto.UsuarioDto;
import com.file.conversor.repository.entity.Pedido;
import com.file.conversor.repository.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BuscaPedidoServiceTest {

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private BuscaPedidoService buscaPedidoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName(value = "Deve retornar todos os pedidos")
    public void testBuscarTodosPedidos() {
        // Mock de dados
        List<Pedido> pedidosMock = new ArrayList<>();
        Pedido pedidoMock = new Pedido();
        pedidoMock.setId(1L);
        pedidosMock.add(pedidoMock);

        // Mock do serviço
        when(pedidoService.buscarTodos()).thenReturn(pedidosMock);

        // Teste
        BuscaPedidoRequestDto buscaPedidoRequestDto = new BuscaPedidoRequestDto();
        List<UsuarioDto> resultado = null;
        try {
            resultado = buscaPedidoService.buscar(buscaPedidoRequestDto);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Verificações
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getPedidos().size());
        assertEquals(1L, resultado.get(0).getPedidos().get(0).getId());
    }

    // Adicione mais testes aqui conforme necessário
}