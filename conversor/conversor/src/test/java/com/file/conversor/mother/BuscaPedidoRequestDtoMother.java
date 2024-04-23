package com.file.conversor.mother;

import com.file.conversor.repository.dto.BuscaPedidoRequestDto;
import com.file.conversor.repository.dto.BuscaPedidoRequestDto.BuscaPedidoRequestDtoBuilder;
import org.springframework.stereotype.Service;

@Service
public class BuscaPedidoRequestDtoMother {

    public static BuscaPedidoRequestDtoBuilder porPedido() {
        return BuscaPedidoRequestDto.builder()
                .pedidoId(15L);
    }

    public static BuscaPedidoRequestDtoBuilder porDataCompra() {
        return BuscaPedidoRequestDto.builder()
                .dataInicial("2024-01-01")
                .dataFinal("2024-02-01");
    }

    public static BuscaPedidoRequestDtoBuilder porPedidoAndDataCompra() {
        return BuscaPedidoRequestDto.builder()
                .pedidoId(15L)
                .dataInicial("2024-01-01")
                .dataFinal("2024-02-01");
    }
}
