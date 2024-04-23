package com.file.conversor.service.validator;

import com.file.conversor.mother.ArquivoMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ArquivoValidatorTest {

    @Autowired
    @InjectMocks
    ArquivoValidator arquivoValidator;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve lançar erro quando arquivo for txt e estiver vazio")
    public void deveLancarErroQuandoArquivoTxtVazio() {
        MultipartFile arquivoTxt = ArquivoMother.textoVazio();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            arquivoValidator.validar(arquivoTxt);
        });

        String mensagemEsperada = "File is empty";
        String mensagemRetornada = exception.getMessage();
        assert(mensagemRetornada.contains(mensagemEsperada));
    }

    @Test
    @DisplayName("Deve lançar erro quando arquivo não for txt")
    public void deveLancarErroQuandoArquivoNaoForTxt() {
        MultipartFile arquivoTxt = ArquivoMother.imagem();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            arquivoValidator.validar(arquivoTxt);
        });

        String mensagemEsperada = "File type is incorrect. Only .txt is allowed";
        String mensagemRetornada = exception.getMessage();
        assert(mensagemRetornada.contains(mensagemEsperada));
    }

    @Test
    @DisplayName("Não deve lançar erro quando arquivo for txt e estiver preenchido")
    public void naoDeveLancarErroQuandoArquivoTxtPreenchido() {
        MultipartFile arquivoTxt = ArquivoMother.textoPrenchido();

        arquivoValidator.validar(arquivoTxt);
    }

}