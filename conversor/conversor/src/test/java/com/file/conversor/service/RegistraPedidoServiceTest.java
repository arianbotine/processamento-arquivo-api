package com.file.conversor.service;

import com.file.conversor.mother.*;
import com.file.conversor.repository.dto.RegistroPedidoDto;
import com.file.conversor.repository.entity.Pedido;
import com.file.conversor.repository.entity.PedidoProduto;
import com.file.conversor.repository.entity.Produto;
import com.file.conversor.repository.entity.Usuario;
import com.file.conversor.service.converter.RegistroPedidoDtoConverter;
import com.file.conversor.service.validator.ArquivoValidator;
import com.file.conversor.service.validator.PedidoValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;

import static org.mockito.Mockito.*;

class RegistraPedidoServiceTest {

    @Mock
    private ArquivoValidator arquivoValidator;

    @Mock
    private PedidoValidator pedidoValidator;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private ProdutoService produtoService;

    @Mock
    private PedidoProdutoService pedidoProdutoService;

    @Mock
    private PedidoService pedidoService;

    @Mock
    private RegistroPedidoDtoConverter registroPedidoDtoConverter;

    @Autowired
    @InjectMocks
    private RegistraPedidoService registraPedidoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve registrar pedido sem erros")
    public void deveRegistrarPedido() throws ParseException, IOException {

        MultipartFile arquivoTxt = ArquivoMother.textoPrenchido();
        InputStream inputStream = arquivoTxt.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String registro = reader.readLine();

        RegistroPedidoDto registroPedidoDto = RegistroPedidoDtoMother.simples().build();
        Usuario usuario = UsuarioMother.simples().build();
        Pedido pedido = PedidoMother.simples().build();
        Produto produto = ProdutoMother.simples().build();

        doNothing().when(arquivoValidator).validar(arquivoTxt);
        doNothing().when(pedidoValidator).validar(registro, 1L);
        when(registroPedidoDtoConverter.toDto(registro)).thenReturn(registroPedidoDto);
        when(usuarioService.registrar(any(Usuario.class))).thenReturn(usuario);
        when(pedidoService.registrar(any(Pedido.class))).thenReturn(pedido);
        when(produtoService.registrar(any(Produto.class))).thenReturn(produto);

        registraPedidoService.lerArquivo(arquivoTxt);

        verify(arquivoValidator).validar(arquivoTxt);
        verify(pedidoValidator).validar(registro, 1L);
        verify(registroPedidoDtoConverter).toDto(registro);
        verify(usuarioService).registrar(any(Usuario.class));
        verify(pedidoService).registrar(any(Pedido.class));
        verify(produtoService).registrar(any(Produto.class));
        verify(pedidoProdutoService).registrar(any(PedidoProduto.class));
    }

    /*
    @Test
    @DisplayName("Deve lançar erro quando registro não tiver 95 caracteres")
    public void deveLancarErroQuandoRegistroNaoValido() {
        String registroInvalido = "  ";

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            registraPedidoService.registrar(registroInvalido);
        });

        String mensagemEsperada = "Invalid format on the line";
        String mensagemRetornada = exception.getMessage();
        assert (mensagemRetornada.contains(mensagemEsperada));
    }
    */
}