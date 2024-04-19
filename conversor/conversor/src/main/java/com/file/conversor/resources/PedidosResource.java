package com.file.conversor.resources;

import com.file.conversor.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;

@RestController
@RequestMapping("/pedidos")
public class PedidosResource {

    @Autowired
    PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<String> registrarPedidos(@RequestParam("file") MultipartFile arquivo) {
        if (!arquivo.isEmpty()) {
            try {
                InputStream inputStream = arquivo.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);

                String linha;
                while (reader.readLine() != null) {
                    linha = reader.readLine();
                    pedidoService.registrarPedido(linha);
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
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            return ResponseEntity.ok("Arquivo vazio");
        }
    }

    @GetMapping
    public ResponseEntity<String> buscarPedidos(@RequestParam("orderId") Long codigoPedido) {

        return ResponseEntity.ok("Arquivo vazio");
    }
}
