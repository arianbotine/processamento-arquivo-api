package com.file.conversor.repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RegistraPedidoResponseDto {

    @JsonProperty("status")
    private HttpStatus status;

    @JsonProperty("message")
    private String mensagem;
}
