package com.file.conversor.service;

import com.file.conversor.repository.dto.RegistraPedidoResponseDto;
import com.file.conversor.repository.dto.RegistroPedidoDto;
import com.file.conversor.repository.entity.Pedido;
import com.file.conversor.repository.entity.PedidoProduto;
import com.file.conversor.repository.entity.Produto;
import com.file.conversor.repository.entity.Usuario;
import com.file.conversor.service.converter.RegistroPedidoDtoConverter;
import com.file.conversor.service.validator.ArquivoValidator;
import com.file.conversor.service.validator.PedidoValidator;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;

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
    ArquivoValidator arquivoValidator;

    @Autowired
    PedidoValidator pedidoValidator;

    @Autowired
    RegistroPedidoDtoConverter registroPedidoDtoConverter;

    @SneakyThrows
    public RegistraPedidoResponseDto lerArquivo(MultipartFile arquivo) {

        long contador = 0L;
        arquivoValidator.validar(arquivo);
        InputStream inputStream = arquivo.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        String linha;
        while ((linha = reader.readLine()) != null) {
            contador = contador + 1;
            pedidoValidator.validar(linha, contador);
            this.registrar(linha);
        }
        reader.close();

        String mensagemSucesso = "File upload completed. {contador} lines read";
        return RegistraPedidoResponseDto.builder()
                .status(HttpStatus.CREATED)
                .mensagem(mensagemSucesso.replace("{contador}", Long.toString(contador)))
                .build();

    }

    @Transactional
    private void registrar(String registro) throws ParseException {

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
