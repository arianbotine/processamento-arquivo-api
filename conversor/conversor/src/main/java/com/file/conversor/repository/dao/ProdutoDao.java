package com.file.conversor.repository.dao;

import com.file.conversor.repository.ProdutoRepository;
import com.file.conversor.repository.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoDao {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto criar(Produto produto) {
        Optional<Produto> produtoOptional  = produtoRepository.findById(produto.getId());
        if (produtoOptional.isPresent()) {
            return produtoOptional.get();
        }
        produtoRepository.save(produto);
        return produto;
    }

}
