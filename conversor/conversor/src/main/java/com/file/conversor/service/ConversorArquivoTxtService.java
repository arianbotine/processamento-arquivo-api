package com.file.conversor.service;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ConversorArquivoTxtService {
    public void processarArquivo (StringBuilder arquivo) {
        int pedidoId = converterStringParaInteger(arquivo.substring(56,65));
        String dataCompra = arquivo.substring(88,96);
        System.out.println(pedidoId + "  " + dataCompra);
    }

    public int converterStringParaInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("Falha ao converter em número o registro: " + string);
        }
    }
}
