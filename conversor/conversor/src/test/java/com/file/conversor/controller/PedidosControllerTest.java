package com.file.conversor.controller;

import com.file.conversor.service.BuscaPedidoService;
import com.file.conversor.service.mapper.UsuarioDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.text.ParseException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PedidosControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    @InjectMocks
    UsuarioDtoMapper usuarioDtoMapper;

    @Mock
    private BuscaPedidoService buscaPedidoServiceMock;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarPedidos() throws ParseException {

        Long pedidoId = 123L;
        String dataInicial = "2024-01-01";
        String dataFinal = "2024-01-31";
        int page = 0;
        int size = 10;

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/orders")
                        .queryParam("orderId", pedidoId)
                        .queryParam("startDate", dataInicial)
                        .queryParam("endDate", dataFinal)
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .build())
                .exchange()
                .expectStatus().isNoContent();
    }
    /*
    @Test
    void buscarPedidos() throws ParseException {

        Long pedidoId = 123L;
        String dataInicial = "2024-01-01";
        String dataFinal = "2024-01-31";
        int page = 0;
        int size = 10;

        List<Usuario> usuarios = List.of(
                UsuarioMother.comUmPedido().id(15L).build(),
                UsuarioMother.comUmPedido().id(16L).build(),
                UsuarioMother.comUmPedido().id(17L).build());
        List<UsuarioDto> usuariosEspperados = usuarioDtoMapper.toUsuarioDtoList(usuarios);

        // Prepare expected output

        // Mock the service call
        when(buscaPedidoServiceMock.buscar(any())).thenReturn(usuariosEspperados);

        // Perform the request and verify the response
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/orders")
                        .queryParam("orderId", pedidoId)
                        .queryParam("startDate", dataInicial)
                        .queryParam("endDate", dataFinal)
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(UsuarioDto.class)
                .isEqualTo(usuariosEspperados);
    }
     */
}