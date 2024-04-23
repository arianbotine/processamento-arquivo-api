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

    public ArquivoMother(String name, String originalFilename, String contentType, byte[] content) {
        this.name = name;
        this.originalFilename = originalFilename;
        this.contentType = contentType;
        this.content = content;
    }

    public static ArquivoMother textoVazio() {
        return new ArquivoMother(
                "file",
                "test-file.txt",
                "text/plain",
                "".getBytes()
        );
    }

    public static ArquivoMother textoPrenchido() {
        return new ArquivoMother(
                "file",
                "test-file.txt",
                "text/plain",
                "conteudo".getBytes()
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
