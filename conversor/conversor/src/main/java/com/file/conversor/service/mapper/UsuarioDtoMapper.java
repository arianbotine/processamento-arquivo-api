package com.file.conversor.service.mapper;

import com.file.conversor.repository.dto.PedidoDto;
import com.file.conversor.repository.dto.ProdutoDto;
import com.file.conversor.repository.dto.UsuarioDto;
import com.file.conversor.repository.entity.Pedido;
import com.file.conversor.repository.entity.Usuario;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioDtoMapper {

    public List<UsuarioDto> toUsuarioDtoList(List<Usuario> usuarios) {
        return usuarios.stream().map(this::toUsuarioDto).collect(Collectors.toList());
    }

    private UsuarioDto toUsuarioDto(Usuario usuario) {
        List<PedidoDto> pedidos = usuario.getPedidos().stream().map(this::toPedidoDto).collect(Collectors.toList());
        return UsuarioDto.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .pedidos(pedidos)
                .build();
    }
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    private PedidoDto toPedidoDto(Pedido pedido) {
        List<ProdutoDto> produtos = pedido.getPedidoProdutos().stream()
                .map(pedidoProduto -> ProdutoDto.builder()
                        .id(pedidoProduto.getProduto().getId())
                        .valor(String.format("%.2f", pedidoProduto.getValor()).replace(',','.'))
                        .build())
                .collect(Collectors.toList());
        return PedidoDto.builder()
                .id(pedido.getId())
                .valorTotal(String.format("%.2f", pedido.getValorTotal()).replace(',','.'))
                .dataCompra(formato.format(pedido.getDataCompra()))
                .produtos(produtos)
                .build();
    }
}
