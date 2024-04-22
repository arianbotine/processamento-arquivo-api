package com.file.conversor.service;

import com.file.conversor.repository.dao.ProdutoDao;
import com.file.conversor.repository.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    ProdutoDao produtoDao;

    public Produto registrar (Long produtoId) {
        return produtoDao.criar(Produto.builder()
                .id(produtoId)
                .build());
    }
}
