package com.file.conversor.service;

import com.file.conversor.repository.dto.*;
import com.file.conversor.repository.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<UsuarioDto> buscar (BuscaPedidoRequestDto buscaPedidoRequestDto) throws ParseException {

        List<Usuario> usuarios = new java.util.ArrayList<>(List.of());
        if (Objects.isNull(buscaPedidoRequestDto.getPedidoId())
                && Objects.isNull(buscaPedidoRequestDto.getDataInicial())
                && Objects.isNull(buscaPedidoRequestDto.getDataFinal())) {

            usuarios = usuarioService.buscarTodos();
        } else if (Objects.nonNull(buscaPedidoRequestDto.getPedidoId())
                && Objects.nonNull(buscaPedidoRequestDto.getDataInicial())
                && Objects.nonNull(buscaPedidoRequestDto.getDataFinal())) {

            Date dataInicial = converterStringParaData(buscaPedidoRequestDto.getDataInicial());
            Date dataFinal = converterStringParaData(buscaPedidoRequestDto.getDataFinal());
            usuarios = usuarioService.buscarPorDataCompraPedidoAndPedido(
                    buscaPedidoRequestDto.getPedidoId(),
                    dataInicial,
                    dataFinal);
        } else if ((Objects.nonNull(buscaPedidoRequestDto.getDataInicial())
                && Objects.nonNull(buscaPedidoRequestDto.getDataFinal()))) {

            Date dataInicial = converterStringParaData(buscaPedidoRequestDto.getDataInicial());
            Date dataFinal = converterStringParaData(buscaPedidoRequestDto.getDataFinal());
            usuarios = usuarioService.buscarPorDataCompraPedido(dataInicial, dataFinal);
        } else if (Objects.nonNull(buscaPedidoRequestDto.getPedidoId())) {

            Usuario usuario = usuarioService.buscarPorPedidoId(buscaPedidoRequestDto.getPedidoId());
            usuarios = List.of(usuario);
        }

        return usuarioService.toListDto(usuarios);
    }

    private Date converterStringParaData(String dataString) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.parse(dataString);
    }
}
