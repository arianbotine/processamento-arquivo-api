package com.file.conversor.service;

import com.file.conversor.repository.dao.ProdutoDao;
import com.file.conversor.repository.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoDao produtoDao;

    public Produto registrar (Produto produto) {

        Optional<Produto> produtoOptional  = produtoDao.findById(produto.getId());
        return produtoOptional.orElseGet(() -> produtoDao.save(produto));
    }
}
