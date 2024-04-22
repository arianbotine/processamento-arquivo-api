package com.file.conversor.service;

import com.file.conversor.repository.entity.Pedido;
import com.file.conversor.repository.entity.Produto;
import com.file.conversor.repository.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RegistrarPedidoService {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    PedidoProdutoService pedidoProdutoService;

    @Autowired
    PedidoService pedidoService;

    @Transactional
    public void registrar(String registro) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");

        Long usuarioId = converterStringParaLong(registro.substring(1,10));
        String usuarioNome = registro.substring(11,55).trim();

        Usuario usuario = usuarioService.registrar(Usuario.builder()
                .id(usuarioId)
                .nome(usuarioNome)
                .build());

        Long pedidoId = converterStringParaLong(registro.substring(56,65));
        Long produtoId = converterStringParaLong(registro.substring(66,75));
        Float pedidoValor = Float.parseFloat(registro.substring(76,87));
        Date dataCompra = formato.parse(registro.substring(87,95));

        Pedido pedido = pedidoService.registrar(Pedido.builder()
                .id(pedidoId)
                .usuario(usuario)
                .valorTotal(pedidoValor)
                .dataCompra(dataCompra)
                .build());

        Produto produto = produtoService.registrar(Produto.builder()
                .id(produtoId)
                .build());
        pedidoProdutoService.registrar(pedido, produto, pedidoValor);

    }

    public Long converterStringParaLong(String string) {
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("Falha ao converter em número o registro: " + string);
        }
    }
}
