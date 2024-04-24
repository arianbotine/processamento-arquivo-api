package com.file.conversor.service.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class ArquivoValidator {

    private final String CONTENT_TYPE_TXT = "text/plain";

    public void validar(MultipartFile arquivo) {

        if (Objects.equals(arquivo.getContentType(), CONTENT_TYPE_TXT) && arquivo.isEmpty()) {
            throw ExceptionValidator.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .mensagem("File is empty")
                    .build();
        } else if (!Objects.equals(arquivo.getContentType(), CONTENT_TYPE_TXT)) {
            throw ExceptionValidator.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .mensagem("File type is incorrect. Only .txt is allowed")
                    .build();
        }
    }
}
