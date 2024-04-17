package com.file.conversor.resource;

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

@RestController
@RequestMapping("/upload")
public class ArquivoResource {

    @PostMapping("/txt")
    public ResponseEntity<String> uploadTxt(@RequestParam("file") MultipartFile arquivo) {
        if (!arquivo.isEmpty()) {
            try {
                InputStream inputStream = arquivo.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);

                String linha;
                StringBuilder conteudo = new StringBuilder();

                while (reader.readLine() != null) {
                    linha = reader.readLine();
                    conteudo.append(linha).append("\n");
                }
                //int a = converterStringParaInteger(linha.substring(0,10));
                reader.close();
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
