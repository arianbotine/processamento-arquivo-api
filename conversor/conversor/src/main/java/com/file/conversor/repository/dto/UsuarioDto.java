package com.file.conversor.repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    @JsonProperty("user_id")
    private Long id;

    @JsonProperty("name")
    private String nome;

    @JsonProperty("orders")
    private List<PedidoDto> pedidos;
}
