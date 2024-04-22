package com.file.conversor.service;

import com.file.conversor.repository.dto.RegistroPedidoDto;
import com.file.conversor.repository.entity.Pedido;
import com.file.conversor.repository.entity.PedidoProduto;
import com.file.conversor.repository.entity.Produto;
import com.file.conversor.repository.entity.Usuario;
import com.file.conversor.service.converter.RegistroPedidoDtoConverter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RegistraPedidoService {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    PedidoProdutoService pedidoProdutoService;

    @Autowired
    PedidoService pedidoService;

    @Autowired
    RegistroPedidoDtoConverter registroPedidoDtoConverter;

    private final long QUANTIDADE_CARACTERES_OBRIGATORIO = 95;

    @Transactional
    public void registrar(String registro) throws ParseException {

        if (registro.length() != QUANTIDADE_CARACTERES_OBRIGATORIO) {
            throw new IllegalStateException("Invalid format on the line");
        }

        RegistroPedidoDto registroPedidoDto =
                registroPedidoDtoConverter.toDto(registro);

        Usuario usuario = usuarioService.registrar(Usuario.builder()
                .id(registroPedidoDto.getUsuarioId())
                .nome(registroPedidoDto.getUsuarioNome())
                .build());

        Pedido pedido = pedidoService.registrar(Pedido.builder()
                .id(registroPedidoDto.getPedidoId())
                .usuario(usuario)
                .valorTotal(registroPedidoDto.getPedidoValor())
                .dataCompra(registroPedidoDto.getDatacompra())
                .build());

        Produto produto = produtoService.registrar(Produto.builder()
                .id(registroPedidoDto.getProdutoId())
                .build());

        pedidoProdutoService.registrar(PedidoProduto.builder()
                .pedido(pedido)
                .produto(produto)
                .valor(registroPedidoDto.getPedidoValor())
                .build());
    }
}
