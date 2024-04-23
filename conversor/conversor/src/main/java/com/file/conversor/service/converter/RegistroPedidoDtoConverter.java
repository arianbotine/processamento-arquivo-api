package com.file.conversor.service.converter;

import com.file.conversor.repository.dto.RegistroPedidoDto;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RegistroPedidoDtoConverter {

    public RegistroPedidoDto toDto(String registro) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");

        Long usuarioId = Long.parseLong(registro.substring(0,10));
        String usuarioNome = registro.substring(10,55).trim();
        Long pedidoId = Long.parseLong(registro.substring(55,65));
        Long produtoId = Long.parseLong(registro.substring(65,75));
        Float pedidoValor = Float.parseFloat(registro.substring(75,87));
        Date dataCompra = formato.parse(registro.substring(87,95));

        return RegistroPedidoDto.builder()
                .usuarioId(usuarioId)
                .usuarioNome(usuarioNome)
                .pedidoId(pedidoId)
                .produtoId(produtoId)
                .pedidoValor(pedidoValor)
                .datacompra(dataCompra)
                .build();
    }
}
