package com.file.conversor.services;

import com.file.conversor.repository.dao.ProdutoDao;
import com.file.conversor.repository.dto.ProdutoDto;
import com.file.conversor.repository.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    ProdutoDao produtoDao;

    public Produto registrar (Long produtoId) {
        return produtoDao.criar(ProdutoDto.builder().id(produtoId).build());
    }
}
