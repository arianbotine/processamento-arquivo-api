package com.file.conversor.mother;

import com.file.conversor.repository.entity.Produto;
import com.file.conversor.repository.entity.Produto.ProdutoBuilder;
import org.springframework.stereotype.Service;

@Service
public class ProdutoMother {

    public ProdutoBuilder simples() {
        return Produto.builder()
                .id(25L);
    }
}
