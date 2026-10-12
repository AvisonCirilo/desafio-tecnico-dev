package br.com.desafio.vendas.io;

import br.com.desafio.vendas.model.Venda;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class VendasJsonReaderTest {

    @Test
    void deveCarregarVendasDoArquivoJson() throws IOException {

        VendasJsonReader reader = new VendasJsonReader();

        List<Venda> vendas = reader.lerVendas();

        assertNotNull(vendas);
        assertFalse(vendas.isEmpty());
        assertEquals(36, vendas.size());
    }
}