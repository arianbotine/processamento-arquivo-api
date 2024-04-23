package com.file.conversor.service;

import com.file.conversor.repository.dto.*;
import com.file.conversor.repository.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class BuscaPedidoService {

    @Autowired
    UsuarioService usuarioService;

    public List<UsuarioDto> buscar(BuscaPedidoRequestDto buscaPedidoRequestDto) throws ParseException {

        List<Usuario> usuarios = new java.util.ArrayList<>(List.of());

        if (Objects.nonNull(buscaPedidoRequestDto.getDataInicial())
                && Objects.nonNull(buscaPedidoRequestDto.getDataFinal())) {

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date dataInicial = formato.parse(buscaPedidoRequestDto.getDataInicial());
            Date dataFinal = formato.parse(buscaPedidoRequestDto.getDataFinal());
            if (Objects.nonNull(buscaPedidoRequestDto.getPedidoId())) {
                usuarios = usuarioService.buscarPorDataCompraPedidoAndPedido(
                        buscaPedidoRequestDto.getPedidoId(),
                        dataInicial,
                        dataFinal);
            } else {
                usuarios = usuarioService.buscarPorDataCompraPedido(dataInicial, dataFinal);
            }
        } else if (Objects.nonNull(buscaPedidoRequestDto.getPedidoId())) {

            Usuario usuario = usuarioService.buscarPorPedidoId(buscaPedidoRequestDto.getPedidoId());
            if (Objects.nonNull(usuario)) {
                usuarios = List.of(usuario);
            }
        } else {
            Pageable pageable = PageRequest.of(
                    buscaPedidoRequestDto.getPage(),
                    buscaPedidoRequestDto.getSize());
            usuarios = usuarioService.buscarTodos(pageable);
        }

        return usuarioService.toListDto(usuarios);
    }
}
