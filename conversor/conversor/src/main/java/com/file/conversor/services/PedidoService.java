package com.file.conversor.services;

import com.file.conversor.repository.dao.PedidoDao;
import com.file.conversor.repository.dto.PedidoDto;
import com.file.conversor.repository.dto.UsuarioDto;
import com.file.conversor.repository.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PedidoService {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PedidoDao pedidoDao;

    public void registrarPedido (String registro) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        Usuario usuario = usuarioService.registrarUsuario(registro);
        Long codigoPedido = converterStringParaInteger(registro.substring(56,65));
        Long produtoId = converterStringParaInteger(registro.substring(66,75));
        Float valor = Float.parseFloat(registro.substring(76,87));
        Date dataCompra = formato.parse(registro.substring(87,95));

        pedidoDao.criar(PedidoDto.builder()
                        .usuario(usuario)
                        .codigo(codigoPedido)
                        .produtoId(produtoId)
                        .valor(valor)
                        .dataCompra(dataCompra)
                        .build());
    }

    public Long converterStringParaInteger(String string) {
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("Falha ao converter em número o registro: " + string);
        }
    }
}
