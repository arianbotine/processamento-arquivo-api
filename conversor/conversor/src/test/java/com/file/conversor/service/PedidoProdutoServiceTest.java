package com.file.conversor.service;

import com.file.conversor.mother.PedidoProdutoMother;
import com.file.conversor.repository.dao.PedidoProdutoDao;
import com.file.conversor.repository.entity.PedidoProduto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.mockito.Mockito.*;

class PedidoProdutoServiceTest {

    @Autowired
    @InjectMocks
    PedidoProdutoService pedidoProdutoService;

    @Mock
    PedidoProdutoDao pedidoProdutoDaoMock;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName(value = "Deve registrar uma relação entre pedido e produto")
    void deveRegistrarRelacaoEntrePedidoProduto() {
        PedidoProduto pedidoProduto =
                PedidoProdutoMother
                        .simples()
                        .build();

        when(pedidoProdutoDaoMock.findByPedidoAndProduto(
                pedidoProduto.getPedido(),
                pedidoProduto.getProduto())).thenReturn(Optional.empty());
        when(pedidoProdutoDaoMock.save(pedidoProduto)).thenReturn(pedidoProduto);

        pedidoProdutoService.registrar(pedidoProduto);

        verify(pedidoProdutoDaoMock).findByPedidoAndProduto(
                pedidoProduto.getPedido(),
                pedidoProduto.getProduto());
        verify(pedidoProdutoDaoMock).save(pedidoProduto);
    }


    @Test
    @DisplayName(value = "Deve atualizar o valor total do produto do pedido caso já exista")
    void deveAtualizarValorProdutoNoPedidoCasoJaExista() {
        PedidoProduto pedidoProdutoNovo = PedidoProdutoMother.simples().valor(100F).build();
        PedidoProduto pedidoProdutoAtual = PedidoProdutoMother.simples().valor(100F).build();
        Float valor = pedidoProdutoNovo.getValor() + pedidoProdutoAtual.getValor();
        PedidoProduto pedidoProdutoFinal = PedidoProdutoMother.simples().valor(valor).build();

        when(pedidoProdutoDaoMock.findByPedidoAndProduto(
                pedidoProdutoNovo.getPedido(),
                pedidoProdutoNovo.getProduto())).thenReturn(Optional.of(pedidoProdutoAtual));
        when(pedidoProdutoDaoMock.save(pedidoProdutoNovo)).thenReturn(pedidoProdutoFinal);

        pedidoProdutoService.registrar(pedidoProdutoNovo);

        verify(pedidoProdutoDaoMock).findByPedidoAndProduto(
                pedidoProdutoNovo.getPedido(),
                pedidoProdutoNovo.getProduto());
        verify(pedidoProdutoDaoMock).save(any(PedidoProduto.class));
    }
}