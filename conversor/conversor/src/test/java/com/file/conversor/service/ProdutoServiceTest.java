package com.file.conversor.service;

import com.file.conversor.mother.ProdutoMother;
import com.file.conversor.repository.dao.ProdutoDao;
import com.file.conversor.repository.entity.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    @Autowired
    @InjectMocks
    ProdutoService produtoService;

    @Mock
    ProdutoDao produtoDaoMock;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName(value = "Deve registrar um novo produto")
    void deveRegistrarNovoProduto() {
        Produto produto = ProdutoMother.simples().build();

        when(produtoDaoMock.findById(produto.getId())).thenReturn(Optional.empty());
        when(produtoDaoMock.save(produto)).thenReturn(produto);

        Produto produtoSalvo = produtoService.registrar(produto);

        assertTrue(Objects.nonNull(produtoSalvo));

        verify(produtoDaoMock).findById(produto.getId());
        verify(produtoDaoMock).save(produto);
    }

    @Test
    @DisplayName(value = "Não deve persistir quando produto já existir")
    void naoDevePersistirQuandoProdutoJaExiste() {
        Produto produto = ProdutoMother.simples().build();

        when(produtoDaoMock.findById(produto.getId())).thenReturn(Optional.of(produto));

        Produto produtoSalvo = produtoService.registrar(produto);

        assertTrue(Objects.nonNull(produtoSalvo));

        verify(produtoDaoMock).findById(produto.getId());
        verify(produtoDaoMock, never()).save(produto);
    }
}