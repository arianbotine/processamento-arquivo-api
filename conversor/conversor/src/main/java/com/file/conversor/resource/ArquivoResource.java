package com.file.conversor.resource;

import com.file.conversor.service.ConversorArquivoTxtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/upload")
public class ArquivoResource {

    @Autowired
    ConversorArquivoTxtService conversorArquivoTxtService;

    @PostMapping("/txt")
    public ResponseEntity<String> uploadTxt(@RequestParam("file") MultipartFile arquivo) {
        if (!arquivo.isEmpty()) {
            try {
                InputStream inputStream = arquivo.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);

                String linha;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

                while (reader.readLine() != null) {
                    linha = reader.readLine();
                    int pedidoId = converterStringParaInteger(linha.substring(55,64));
                    String dataCompra = linha.substring(87,95);

                    LocalDate data = LocalDate.parse(dataCompra, formatter);
                    System.out.println(pedidoId + "  " + dataCompra);
                }

                reader.close();
                //conversorArquivoTxtService.processarArquivo(conteudo);
                return ResponseEntity.ok("OK");
            } catch (IOException e) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Falha ao processar o arquivo");
            } catch (NumberFormatException e) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(e.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.ok("Arquivo vazio");
        }
    }

    public int converterStringParaInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("Falha ao converter em número o registro: " + string);
        }
    }
}
