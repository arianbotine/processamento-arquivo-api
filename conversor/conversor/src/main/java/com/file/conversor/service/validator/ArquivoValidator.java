package com.file.conversor.service.validator;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class ArquivoValidator {

    private final String CONTENT_TYPE_TXT = "text/plain";

    public void validar(MultipartFile arquivo) {

        if (Objects.equals(arquivo.getContentType(), CONTENT_TYPE_TXT) && arquivo.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        } else if (!Objects.equals(arquivo.getContentType(), CONTENT_TYPE_TXT)) {
            throw new IllegalArgumentException("File type is incorrect. Only .txt is allowed");
        }
    }
}
