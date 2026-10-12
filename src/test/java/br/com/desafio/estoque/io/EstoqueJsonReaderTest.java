package br.com.desafio.estoque.io;

import br.com.desafio.estoque.model.Produto;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EstoqueJsonReaderTest {

    @Test
    void deveCarregarProdutosDoArquivoJson() throws IOException {

        EstoqueJsonReader reader = new EstoqueJsonReader();

        List<Produto> produtos = reader.lerEstoque();

        assertNotNull(produtos);
        assertFalse(produtos.isEmpty());
        assertEquals(5, produtos.size());
    }
}