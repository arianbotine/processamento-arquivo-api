package com.file.conversor.service.converter;

import com.file.conversor.mother.RegistroPedidoDtoMother;
import com.file.conversor.repository.dto.RegistroPedidoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistroPedidoDtoConverterTest {

    @Autowired
    @InjectMocks
    RegistroPedidoDtoConverter registroPedidoDtoConverter;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private final String REGISTRO = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";

    @Test
    @DisplayName(value = "Deve registrar um novo produto")
    void deveRegistrarNovoProduto() throws ParseException {

        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        RegistroPedidoDto registroPedidoDtoEsperado =
                RegistroPedidoDtoMother.simples()
                        .usuarioId(70L)
                        .usuarioNome("Palmer Prosacco")
                        .pedidoId(753L)
                        .produtoId(3L)
                        .pedidoValor(1836.74F)
                        .datacompra(formato.parse("20210308"))
                        .build();

        RegistroPedidoDto registroPedidoDto = registroPedidoDtoConverter.toDto(REGISTRO);

        assertEquals(registroPedidoDto, registroPedidoDtoEsperado);
    }
}