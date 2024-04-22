package com.file.conversor.service;

import com.file.conversor.repository.dto.BuscaPedidoRequestDto;
import com.file.conversor.repository.dto.PedidoDto;
import com.file.conversor.repository.dto.ProdutoDto;
import com.file.conversor.repository.dto.UsuarioDto;
import com.file.conversor.repository.entity.Pedido;
import com.file.conversor.repository.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BuscaPedidoService {

    @Autowired
    PedidoService pedidoService;

    public List<UsuarioDto> buscar (BuscaPedidoRequestDto buscaPedidoRequestDto) throws ParseException {
        List<Pedido> pedidos = new java.util.ArrayList<>(List.of());

        if (Objects.isNull(buscaPedidoRequestDto.getPedidoId())
                && Objects.isNull(buscaPedidoRequestDto.getDataInicial())
                && Objects.isNull(buscaPedidoRequestDto.getDataFinal())) {
            pedidos = pedidoService.buscarTodos();
            return retornarPedidos(pedidos);
        } else if ((buscaPedidoRequestDto.getDataInicial() != null)
                && (buscaPedidoRequestDto.getDataFinal() != null)) {
            Date dataInicial = converterStringParaData(buscaPedidoRequestDto.getDataInicial());
            Date dataFinal = converterStringParaData(buscaPedidoRequestDto.getDataInicial());
            pedidos = pedidoService.buscarPorDataCompra(dataInicial, dataFinal);
        }

        if (buscaPedidoRequestDto.getPedidoId() != null) {
            if (pedidos.isEmpty()) {
                Pedido pedidoUnico = pedidoService.buscarPorId(buscaPedidoRequestDto.getPedidoId());
                pedidos.add(pedidoUnico);
            } else {
                pedidos = pedidos.stream()
                        .filter((pedido) ->
                                pedido.getId().equals(buscaPedidoRequestDto.getPedidoId()))
                        .collect(Collectors.toList());
            }
        }

        return retornarPedidos(pedidos);
    }

    public List<UsuarioDto> retornarPedidos (List<Pedido> pedidos) {

        return pedidos.stream().map(pedido -> {

            List<ProdutoDto> produtos =
                    pedido.getPedidoProdutos().stream().map(pedidoProduto ->
                                    ProdutoDto.builder()
                                            .id(pedidoProduto.getProduto().getId())
                                            .valor(pedidoProduto.getValor().toString())
                                            .build())
                            .collect(Collectors.toList());

            PedidoDto pedidoDto = PedidoDto.builder()
                    .id(pedido.getId())
                    .valorTotal(pedido.getValorTotal())
                    .dataCompra(pedido.getDataCompra().toString())
                    .produtos(produtos)
                    .build();

            Usuario usuario = pedido.getUsuario();
            return UsuarioDto.builder()
                    .id(usuario.getId())
                    .nome(usuario.getNome())
                    .pedidos(List.of(pedidoDto))
                    .build();

        }).collect(Collectors.toList());
    }

    public Date converterStringParaData(String dataString) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.parse(dataString);
    }
}
