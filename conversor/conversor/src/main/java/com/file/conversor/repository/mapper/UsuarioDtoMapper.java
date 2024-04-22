package com.file.conversor.repository.mapper;

import com.file.conversor.repository.dto.PedidoDto;
import com.file.conversor.repository.dto.ProdutoDto;
import com.file.conversor.repository.dto.UsuarioDto;
import com.file.conversor.repository.entity.Pedido;
import com.file.conversor.repository.entity.Usuario;
import org.springframework.stereotype.Service;

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

    private PedidoDto toPedidoDto(Pedido pedido) {
        List<ProdutoDto> produtos = pedido.getPedidoProdutos().stream()
                .map(pedidoProduto -> ProdutoDto.builder()
                        .id(pedidoProduto.getProduto().getId())
                        .valor(pedidoProduto.getValor().toString())
                        .build())
                .collect(Collectors.toList());
        return PedidoDto.builder()
                .id(pedido.getId())
                .valorTotal(pedido.getValorTotal())
                .dataCompra(pedido.getDataCompra().toString())
                .produtos(produtos)
                .build();
    }
}
