package com.file.conversor.repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.file.conversor.repository.entity.Pedido;
import com.file.conversor.repository.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
