package com.file.conversor.controller;

import com.file.conversor.repository.dto.BuscaPedidoRequestDto;
import com.file.conversor.repository.dto.RegistraPedidoResponseDto;
import com.file.conversor.repository.dto.UsuarioDto;
import com.file.conversor.service.BuscaPedidoService;
import com.file.conversor.service.RegistraPedidoService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class PedidosController {

    @Autowired
    RegistraPedidoService registraPedidoService;

    @Autowired
    BuscaPedidoService buscaPedidoService;

    @SneakyThrows
    @PostMapping
    public ResponseEntity<RegistraPedidoResponseDto> registrarPedidos(@RequestParam("file") MultipartFile arquivo) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registraPedidoService.lerArquivo(arquivo));
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
