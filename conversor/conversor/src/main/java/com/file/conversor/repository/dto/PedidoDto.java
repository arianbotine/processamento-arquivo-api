package com.file.conversor.repository.dto;

import com.file.conversor.repository.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {

    private Long id;

    private Long codigo;

    private Long produtoId;

    private Float valor;

    private Date dataCompra;

    private Usuario usuario;
}
