package com.file.conversor.resource;

import com.file.conversor.repository.dto.BuscaPedidoRequestDto;
import com.file.conversor.repository.dto.UsuarioDto;
import com.file.conversor.service.BuscaPedidoService;
import com.file.conversor.service.RegistrarPedidoService;
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
import java.util.List;

@RestController
@RequestMapping("/orders")
public class PedidosResource {

    @Autowired
    RegistrarPedidoService registrarPedidoService;

    @Autowired
    BuscaPedidoService buscaPedidoService;

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
                    registrarPedidoService.registrar(linha);
                }

                reader.close();
                //conversorArquivoTxtService.processarArquivo(conteudo);
                return ResponseEntity.status(HttpStatus.CREATED).body("Recurso criado com sucesso!");
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
    public ResponseEntity<List<UsuarioDto>> buscarPedidos(
            @RequestParam(value = "orderId", required = false) Long pedidoId,
            @RequestParam(value = "startDate", required = false) String dataInicial,
            @RequestParam(value = "endDate", required = false) String dataFinal) throws ParseException {

        return ResponseEntity.ok(buscaPedidoService.buscar(
                BuscaPedidoRequestDto.builder()
                        .pedidoId(pedidoId)
                        .dataInicial(dataInicial)
                        .dataFinal(dataFinal)
                        .build()));
    }
}
