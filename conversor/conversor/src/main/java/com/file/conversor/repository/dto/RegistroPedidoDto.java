package com.file.conversor.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RegistroPedidoDto {

    private Long usuarioId;

    private String usuarioNome;

    private Long pedidoId;

    private Long produtoId;

    private Float pedidoValor;

    private Date datacompra;
}
