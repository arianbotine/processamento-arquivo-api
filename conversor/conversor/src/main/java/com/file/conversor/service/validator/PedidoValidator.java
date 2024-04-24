package com.file.conversor.service.validator;

import com.file.conversor.repository.dto.exception.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PedidoValidator {

    private final long QUANTIDADE_CARACTERES_OBRIGATORIO = 95;

    public void validar(String registro, long contador) {

        if (registro.length() != QUANTIDADE_CARACTERES_OBRIGATORIO) {
            throw ExceptionDto.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .mensagem("Invalid format on the line: " + contador)
                    .build();
        }
    }
}
