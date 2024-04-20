package com.file.conversor.repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto {

    @JsonProperty("product_id")
    private Long id;

    @JsonProperty("value")
    private String valor;
}
