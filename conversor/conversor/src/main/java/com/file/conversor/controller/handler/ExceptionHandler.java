package com.file.conversor.controller.handler;

import com.file.conversor.repository.dto.ErroResponseDto;
import com.file.conversor.repository.dto.exception.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.ParseException;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({
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

    @org.springframework.web.bind.annotation.ExceptionHandler(
            MultipartException.class)
    public ResponseEntity<ErroResponseDto> handleInvalidFileException(MultipartException ex) {
        ErroResponseDto errorResponse = ErroResponseDto.builder()
                .status(HttpStatus.BAD_REQUEST)
                .mensagem("Select the file to be uploaded again")
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({
            ExceptionDto.class})
    public ResponseEntity<ErroResponseDto> handlerExceptionValidator(ExceptionDto ex) {
        ErroResponseDto errorResponse = ErroResponseDto.builder()
                .status(ex.getStatus())
                .mensagem(ex.getMensagem())
                .build();

        log.warn("Exception", ex);

        return ResponseEntity.status(ex.getStatus()).body(errorResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({
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
