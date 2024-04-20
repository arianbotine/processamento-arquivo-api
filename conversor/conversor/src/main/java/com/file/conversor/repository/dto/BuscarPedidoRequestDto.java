package com.file.conversor.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BuscarPedidoRequestDto {

    private Long pedidoId;

    private Long dataInicial;

    private Long dataFinal;
}
