package com.file.conversor.service.validator;

import com.file.conversor.mother.ArquivoMother;
import com.file.conversor.repository.dto.exception.ExceptionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.*;

class PedidoValidatorTest {

    @Autowired
    @InjectMocks
    PedidoValidator pedidoValidator;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve lançar erro quando arquivo conter uma linha diferente de 95 caracteres")
    public void deveLancarErroQuandoLinhaInvalida() throws IOException {

        MultipartFile arquivoTxt = ArquivoMother.umaLinhaInvalida();
        InputStream inputStream = arquivoTxt.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String registro = reader.readLine();
        long contador = 1L;
        ExceptionDto exception = assertThrows(ExceptionDto.class, () -> {
            pedidoValidator.validar(registro, contador);
        });

        String mensagemEsperada = "Invalid format on the line: " + contador;
        String mensagemRetornada = exception.getMensagem();
        assert (mensagemRetornada.contains(mensagemEsperada));
    }

    @Test
    @DisplayName("Não deve lançar erro quando arquivo conter apenas linhas com 95 caracteres")
    public void naoDeveLancarErroQuandoArquivoConterSomenteLinhasValidas() throws IOException {

        MultipartFile arquivoTxt = ArquivoMother.umaLinhaValida();
        InputStream inputStream = arquivoTxt.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String registro = reader.readLine();
        long contador = 1L;

        pedidoValidator.validar(registro, contador);

    }

}