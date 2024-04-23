package com.file.conversor.repository;

import com.file.conversor.mother.PedidoMother;
import com.file.conversor.mother.PedidoProdutoMother;
import com.file.conversor.mother.ProdutoMother;
import com.file.conversor.mother.UsuarioMother;
import com.file.conversor.repository.dao.PedidoProdutoDao;
import com.file.conversor.repository.entity.Pedido;
import com.file.conversor.repository.entity.PedidoProduto;
import com.file.conversor.repository.entity.Produto;
import com.file.conversor.repository.entity.Usuario;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
class PedidoProdutoDaoTest {

    @Autowired
    PedidoProdutoDao pedidoProdutoDao;

    @Autowired
    EntityManager entityManager;

    @BeforeEach
    public void criarRegistros() {
        Usuario usuario = UsuarioMother.simples().build();
        Pedido pedido = PedidoMother.simples().usuario(usuario).build();
        Produto produto = ProdutoMother.simples().build();

        this.criarUsuario(usuario);
        this.criarProduto(produto);
        this.criarPedido(pedido);
        this.criarPedidoProduto(PedidoProdutoMother
                .semPersistir()
                .pedido(pedido)
                .produto(produto)
                .build());
    }

    @Test
    @DisplayName(value = "Deve retornar registro quando pesquisar por pedido e produto que existem persistidos")
    void deveRetornarRegistroPorPedidoProdutoExistente() {
        Pedido pedido = PedidoMother.simples().build();
        Produto produto = ProdutoMother.simples().build();

        Optional<PedidoProduto> resultado = this.pedidoProdutoDao.findByPedidoAndProduto(pedido, produto);

        assertTrue(resultado.isPresent());
    }

    @Test
    @DisplayName(value = "Não deve retornar registro quando pesquisar por pedido e produto inexistentes")
    void naoDeveRetornarRegistroPorPedidoProdutoNaoExistentes() {
        Pedido pedido = PedidoMother.simples().id(79L).build();
        Produto produto = Produto.builder().id(27L).build();

        Optional<PedidoProduto> resultado = this.pedidoProdutoDao.findByPedidoAndProduto(pedido, produto);

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName(value = "Deve retornar registro por pedido existente")
    void deveRetornarRegistroPorPedidoExistente() {
        Pedido pedido = PedidoMother.simples().build();

        List<PedidoProduto> resultado = this.pedidoProdutoDao.findByPedido(pedido);

        assertEquals(resultado.size(), 1);
        assertTrue(resultado.stream().findFirst().isPresent());
        assertEquals(resultado.stream().findFirst().get().getPedido().getId(), pedido.getId());
    }

    @Test
    @DisplayName(value = "Não deve retornar registro por pedido não existente")
    void naoDeveRetornarRegistroPorPedidoNaoExistente() {
        Pedido pedido = PedidoMother.simples().id(79L).build();

        List<PedidoProduto> resultado = this.pedidoProdutoDao.findByPedido(pedido);

        assertEquals(resultado.size(), 0);
    }

    private void criarUsuario(Usuario usuario) {
        this.entityManager.persist(usuario);
    }

    private void criarProduto(Produto produto) {
        this.entityManager.persist(produto);
    }

    private void criarPedido(Pedido pedido) {
        this.entityManager.persist(pedido);
    }

    private void criarPedidoProduto(PedidoProduto pedidoProduto) {
        this.entityManager.persist(pedidoProduto);
    }
}