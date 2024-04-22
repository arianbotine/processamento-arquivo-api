package com.file.conversor.mother;

import com.file.conversor.repository.entity.Produto;
import com.file.conversor.repository.entity.Produto.ProdutoBuilder;

public class ProdutoMother {

    public static ProdutoBuilder simples() {
        return Produto.builder()
                .id(25L);
    }
}
