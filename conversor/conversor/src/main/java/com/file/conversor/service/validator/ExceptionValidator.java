package com.file.conversor.service.validator;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
@Builder

public class ExceptionValidator extends RuntimeException {
    private final HttpStatus status;
    private final String mensagem;
}
