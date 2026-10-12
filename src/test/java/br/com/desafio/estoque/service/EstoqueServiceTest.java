package br.com.desafio.estoque.service;

import br.com.desafio.estoque.model.Movimentacao;
import br.com.desafio.estoque.model.Produto;
import br.com.desafio.estoque.model.TipoMovimentacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstoqueServiceTest {

    private EstoqueService service;
    private Produto produto;

    @BeforeEach
    void setUp() {

        service = new EstoqueService();

        produto = new Produto(
                101,
                "Caneta Azul",
                150
        );
    }

    @Test
    void deveAdicionarProdutoAoEstoque() {

        Movimentacao movimentacao =
                service.movimentarEstoque(
                        produto,
                        TipoMovimentacao.ENTRADA,
                        50
                );

        assertEquals(
                200,
                produto.getEstoque()
        );

        assertEquals(
                200,
                movimentacao.getEstoqueFinal()
        );

        assertNotNull(
                movimentacao.getId()
        );

        assertEquals(
                "Entrada de mercadoria",
                movimentacao.getDescricao()
        );
    }

    @Test
    void deveRetirarProdutoDoEstoque() {

        Movimentacao movimentacao =
                service.movimentarEstoque(
                        produto,
                        TipoMovimentacao.SAIDA,
                        50
                );

        assertEquals(
                100,
                produto.getEstoque()
        );

        assertEquals(
                100,
                movimentacao.getEstoqueFinal()
        );
    }

    @Test
    void naoDevePermitirEstoqueNegativo() {

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> service.movimentarEstoque(
                                produto,
                                TipoMovimentacao.SAIDA,
                                200
                        )
                );

        assertEquals(
                "Estoque insuficiente para realizar a saída.",
                exception.getMessage()
        );
    }

    @Test
    void naoDevePermitirQuantidadeZero() {

        assertThrows(
                IllegalArgumentException.class,
                () -> service.movimentarEstoque(
                        produto,
                        TipoMovimentacao.ENTRADA,
                        0
                )
        );
    }
}