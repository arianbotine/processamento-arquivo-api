package com.file.conversor.mother;

import com.file.conversor.repository.dto.RegistroPedidoDto;
import com.file.conversor.repository.dto.RegistroPedidoDto.RegistroPedidoDtoBuilder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegistroPedidoDtoMother {

    public static RegistroPedidoDtoBuilder simples() {
        return RegistroPedidoDto.builder()
                .usuarioId(15L)
                .usuarioNome("Palmer Prosacco")
                .pedidoId(459L)
                .produtoId(10L)
                .pedidoValor(154.78F)
                .datacompra(new Date(2024, 3, 15));
    }
}
