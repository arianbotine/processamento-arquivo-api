package com.file.conversor.repository.dto;

import com.file.conversor.repository.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {

    private Long id;

    private Date dataCompra;

    private Usuario usuario;

    private Float valorTotal;
}
