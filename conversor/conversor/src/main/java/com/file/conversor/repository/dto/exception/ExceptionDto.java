package com.file.conversor.repository.dto.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
@Builder
public class ExceptionDto extends RuntimeException {

    private final HttpStatus status;

    private final String mensagem;
}
