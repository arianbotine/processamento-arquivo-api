package com.file.conversor.service;

import com.file.conversor.repository.dao.UsuarioDao;
import com.file.conversor.repository.dto.UsuarioDto;
import com.file.conversor.repository.entity.Usuario;
import com.file.conversor.service.mapper.UsuarioDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioDao usuarioDao;

    @Autowired
    UsuarioDtoMapper usuarioDtoMapper;

    public Usuario registrar(Usuario usuario) {

        Optional<Usuario> usuarioOptional = usuarioDao.findById(usuario.getId());
        if (usuarioOptional.isPresent()) {
            Usuario currentUsuario = usuarioOptional.get();
            if (currentUsuario.getNome().equals(usuario.getNome())) {
                return currentUsuario;
            }
            currentUsuario.setNome(usuario.getNome());
            usuario = currentUsuario;
        }
        return usuarioDao.save(usuario);
    }

    public Usuario buscarPorPedidoId(Long pedidoId) {
        return usuarioDao.findUsuarioByPedido(pedidoId);
    }

    public List<Usuario> buscarPorDataCompraPedido(Date dataInicial, Date dataFinal) {
        return usuarioDao.findUsuarioByDataCompraPedidoBetween(dataInicial, dataFinal);
    }

    public List<Usuario> buscarPorDataCompraPedidoAndPedido(Long pedidoId, Date dataInicial, Date dataFinal) {
        return usuarioDao.findUsuarioByPedidoAndDataCompraPedidoBetween(pedidoId, dataInicial, dataFinal);
    }

    public List<Usuario> buscarTodos(Pageable pageable) {
        return usuarioDao.findAll(pageable).getContent();
    }

    public List<UsuarioDto> toListDto(List<Usuario> usuarios) {
        return usuarioDtoMapper.toUsuarioDtoList(usuarios);
    }
}
