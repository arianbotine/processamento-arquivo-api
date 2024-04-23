package com.file.conversor.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BuscaPedidoRequestDto {

    private Long pedidoId;

    private String dataInicial;

    private String dataFinal;

    @Builder.Default
    private int page = 0;

    @Builder.Default
    private int size = 10;
}
