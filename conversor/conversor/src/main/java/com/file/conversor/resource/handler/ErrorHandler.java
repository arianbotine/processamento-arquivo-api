package com.file.conversor.resource.handler;

import com.file.conversor.repository.dto.ErroResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.text.ParseException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({ParseException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErroResponseDto> handleInvalidDataProvided(Exception ex) {
        ErroResponseDto errorResponse = ErroResponseDto.builder()
                .status(HttpStatus.BAD_REQUEST)
                .mensagem("Invalid data provided")
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
