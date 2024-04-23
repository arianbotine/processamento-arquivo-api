package com.file.conversor.controller;

import com.file.conversor.repository.dto.BuscaPedidoRequestDto;
import com.file.conversor.repository.dto.RegistraPedidoResponseDto;
import com.file.conversor.repository.dto.UsuarioDto;
import com.file.conversor.service.BuscaPedidoService;
import com.file.conversor.service.RegistraPedidoService;
import com.file.conversor.service.validator.ArquivoValidator;
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
import java.util.Objects;

@RestController
@RequestMapping("/orders")
public class PedidosController {

    @Autowired
    RegistraPedidoService registraPedidoService;

    @Autowired
    BuscaPedidoService buscaPedidoService;

    @Autowired
    ArquivoValidator arquivoValidator;

    @PostMapping
    public ResponseEntity<RegistraPedidoResponseDto> registrarPedidos(@RequestParam("file") MultipartFile arquivo) {
        long contador = 0L;
        try {
            arquivoValidator.validar(arquivo);
            if (Objects.equals(arquivo.getContentType(), "text/plain") && arquivo.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        RegistraPedidoResponseDto.builder()
                                .status(HttpStatus.BAD_REQUEST)
                                .mensagem("File is empty")
                                .build());
            } else if (!Objects.equals(arquivo.getContentType(), "text/plain")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        RegistraPedidoResponseDto.builder()
                                .status(HttpStatus.BAD_REQUEST)
                                .mensagem("File type is incorrect. Only .txt is allowed")
                                .build());
            }
            InputStream inputStream = arquivo.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);


            String linha;
            while ((linha = reader.readLine()) != null) {
                contador = contador + 1;
                registraPedidoService.registrar(linha);
            }

            reader.close();
            String mensagemSucesso = "File upload completed. {contador} lines read";
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    RegistraPedidoResponseDto.builder()
                            .status(HttpStatus.CREATED)
                            .mensagem(mensagemSucesso.replace("{contador}", Long.toString(contador)))
                            .build());
        } catch (IllegalStateException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(RegistraPedidoResponseDto.builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .mensagem(e.getLocalizedMessage() + ": " + contador)
                            .build());
        } catch (IOException | ParseException | IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(RegistraPedidoResponseDto.builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .mensagem(e.getLocalizedMessage())
                            .build());
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> buscarPedidos(
            @RequestParam(value = "orderId", required = false) Long pedidoId,
            @RequestParam(value = "startDate", required = false) String dataInicial,
            @RequestParam(value = "endDate", required = false) String dataFinal,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) throws ParseException {

        List<UsuarioDto> usuarios = buscaPedidoService.buscar(
                BuscaPedidoRequestDto.builder()
                        .pedidoId(pedidoId)
                        .dataInicial(dataInicial)
                        .dataFinal(dataFinal)
                        .page(page)
                        .size(size)
                        .build());

        if (!usuarios.isEmpty()) {
            return ResponseEntity.ok(usuarios);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
