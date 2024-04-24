package com.file.conversor.controller.handler;

import com.file.conversor.repository.dto.ErroResponseDto;
import com.file.conversor.service.validator.ExceptionValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.ParseException;

@RestControllerAdvice
@Slf4j
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            ParseException.class,
            MethodArgumentTypeMismatchException.class,
            IllegalArgumentException.class})
    public ResponseEntity<ErroResponseDto> handleInvalidDataProvided(Exception ex) {
        ErroResponseDto errorResponse = ErroResponseDto.builder()
                .status(HttpStatus.BAD_REQUEST)
                .mensagem("Invalid data provided")
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler({
            ExceptionValidator.class})
    public ResponseEntity<ErroResponseDto> handlerExceptionValidator(ExceptionValidator ex) {
        ErroResponseDto errorResponse = ErroResponseDto.builder()
                .status(ex.getStatus())
                .mensagem(ex.getMensagem())
                .build();

        log.warn("Exception", ex);

        return ResponseEntity.status(ex.getStatus()).body(errorResponse);
    }

    @ExceptionHandler({
            Exception.class})
    public ResponseEntity<ErroResponseDto> handleInternalServerError(Exception ex) {
        ErroResponseDto errorResponse = ErroResponseDto.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .mensagem("Internal server error")
                .build();

        log.error("Exception", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
