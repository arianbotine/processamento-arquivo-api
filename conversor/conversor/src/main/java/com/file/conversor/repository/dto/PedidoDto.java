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
public class PedidoDto {

    @JsonProperty("order_id")
    private Long id;

    @JsonProperty("total")
    private Float valorTotal;

    @JsonProperty("date")
    private String dataCompra;

    @JsonProperty("products")
    private List<ProdutoDto> produtos;
}
