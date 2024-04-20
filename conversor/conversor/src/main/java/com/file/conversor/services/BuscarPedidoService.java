package com.file.conversor.services;

import com.file.conversor.repository.dao.PedidoProdutoDao;
import com.file.conversor.repository.dto.PedidoDto;
import com.file.conversor.repository.dto.UsuarioDto;
import com.file.conversor.repository.entity.Pedido;
import com.file.conversor.repository.entity.PedidoProduto;
import com.file.conversor.repository.entity.Produto;
import com.file.conversor.repository.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuscarPedidoService {

    @Autowired
    PedidoProdutoService pedidoProdutoService;

    public UsuarioDto buscar (Long pedidoId) {
        List<PedidoProduto> listaPedidoProduto = pedidoProdutoService.buscar(pedidoId);

        List<PedidoDto> listaPedidoDto = new java.util.ArrayList<>(List.of());
        for (PedidoProduto pedidoProduto : listaPedidoProduto) {
            listaPedidoDto.add(PedidoDto.builder()
                    .id(pedidoProduto.getPedido().getId())
                    .valorTotal(pedidoProduto.getPedido().getValorTotal())
                    .dataCompra(pedidoProduto.getPedido().getDataCompra().toString())
                    .build());
        }
        Usuario usuario;
        //listaPedidoProduto.stream().findFirst().ifPresent();
        return UsuarioDto.builder()
                .build();
    }
}
