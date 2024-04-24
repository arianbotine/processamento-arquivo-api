package com.file.conversor.mother;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ArquivoMother implements MultipartFile {

    private final String name;
    private final String originalFilename;
    private final String contentType;
    private final byte[] content;

    private static final String REGISTRO_VALIDO_1 = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";

    private static final String REGISTRO_INVALIDO_1 = "0000000070                             Palmer Prosacco00000007530000000003     1836.7420210308";

    public ArquivoMother(String name, String originalFilename, String contentType, byte[] content) {
        this.name = name;
        this.originalFilename = originalFilename;
        this.contentType = contentType;
        this.content = content;
    }

    public static ArquivoMother vazio() {
        return new ArquivoMother(
                "file",
                "test-file.txt",
                "text/plain",
                "".getBytes()
        );
    }

    public static ArquivoMother umaLinhaValida() {
        return new ArquivoMother(
                "file",
                "test-file.txt",
                "text/plain",
                REGISTRO_VALIDO_1.getBytes()
        );
    }

    public static ArquivoMother umaLinhaInvalida() {
        return new ArquivoMother(
                "file",
                "test-file.txt",
                "text/plain",
                REGISTRO_INVALIDO_1.getBytes()
        );
    }

    public static ArquivoMother imagem() {
        return new ArquivoMother(
                "file",
                "test-file.txt",
                "image/jpg",
                "".getBytes()
        );
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOriginalFilename() {
        return originalFilename;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean isEmpty() {
        return content.length == 0;
    }

    @Override
    public long getSize() {
        return content.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return content;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(content);
    }

    @Override
    public void transferTo(java.io.File dest) throws IOException, IllegalStateException {
        throw new UnsupportedOperationException("Method not supported");
    }
}
