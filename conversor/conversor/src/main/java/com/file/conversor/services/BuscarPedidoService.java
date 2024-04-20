package com.file.conversor.services;

import com.file.conversor.repository.PedidoRepository;
import com.file.conversor.repository.dao.PedidoProdutoDao;
import com.file.conversor.repository.dto.PedidoDto;
import com.file.conversor.repository.dto.ProdutoDto;
import com.file.conversor.repository.dto.UsuarioDto;
import com.file.conversor.repository.entity.Pedido;
import com.file.conversor.repository.entity.PedidoProduto;
import com.file.conversor.repository.entity.Produto;
import com.file.conversor.repository.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuscarPedidoService {

    @Autowired
    PedidoProdutoService pedidoProdutoService;

    @Autowired
    PedidoRepository pedidoRepository;

    public List<UsuarioDto> buscar (Long pedidoId) {
        List<Pedido> pedidos = new java.util.ArrayList<>(List.of());

        Pedido pedidoUnico = pedidoRepository.findById(pedidoId).get();

        pedidos.add(pedidoUnico);
        return pedidos.stream().map(pedido -> {
            Usuario usuario = pedido.getUsuario();
            UsuarioDto UsuarioDto = new UsuarioDto();
            UsuarioDto.setId(usuario.getId());
            UsuarioDto.setNome(usuario.getNome());

            PedidoDto PedidoDto = new PedidoDto();
            PedidoDto.setId(pedido.getId());
            PedidoDto.setValorTotal(pedido.getValorTotal());
            PedidoDto.setDataCompra(pedido.getDataCompra().toString());

            List<ProdutoDto> ProdutoDtos = pedido.getPedidoProdutos().stream().map(pp -> {
                ProdutoDto ProdutoDto = new ProdutoDto();
                ProdutoDto.setId(pp.getProduto().getId());
                ProdutoDto.setValor(pp.getValor().toString());
                return ProdutoDto;
            }).collect(Collectors.toList());

            PedidoDto.setProdutos(ProdutoDtos);
            UsuarioDto.setPedidos(List.of(PedidoDto));
            return UsuarioDto;
        }).collect(Collectors.toList());
    }
}
