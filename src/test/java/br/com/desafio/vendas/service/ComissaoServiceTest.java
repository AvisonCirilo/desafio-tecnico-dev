package br.com.desafio.vendas.service;

import br.com.desafio.vendas.model.Venda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ComissaoServiceTest {

    private ComissaoService service;

    @BeforeEach
    void setUp() {
        service = new ComissaoService();
    }

    @Test
    void naoDeveGerarComissaoParaVendaAbaixoDeCem() {

        Venda venda = new Venda(
                "Teste",
                new BigDecimal("99.99")
        );

        BigDecimal resultado =
                service.calcularComissao(venda);

        assertBigDecimalEquals(
                "0",
                resultado
        );
    }

    @Test
    void deveAplicarUmPorcentoParaVendaDeCemReais() {

        Venda venda = new Venda(
                "Teste",
                new BigDecimal("100.00")
        );

        BigDecimal resultado =
                service.calcularComissao(venda);

        assertBigDecimalEquals(
                "1.00",
                resultado
        );
    }

    @Test
    void deveAplicarUmPorcentoAbaixoDeQuinhentos() {

        Venda venda = new Venda(
                "Teste",
                new BigDecimal("499.99")
        );

        BigDecimal resultado =
                service.calcularComissao(venda);

        assertBigDecimalEquals(
                "4.9999",
                resultado
        );
    }

    @Test
    void deveAplicarCincoPorcentoAPartirDeQuinhentos() {

        Venda venda = new Venda(
                "Teste",
                new BigDecimal("500.00")
        );

        BigDecimal resultado =
                service.calcularComissao(venda);

        assertBigDecimalEquals(
                "25.00",
                resultado
        );
    }

    @Test
    void deveSomarComissoesDoMesmoVendedor() {

        List<Venda> vendas = List.of(
                new Venda(
                        "João",
                        new BigDecimal("100.00")
                ),
                new Venda(
                        "João",
                        new BigDecimal("500.00")
                )
        );

        Map<String, BigDecimal> resultado =
                service.calcularComissoesPorVendedor(vendas);

        assertBigDecimalEquals(
                "26.00",
                resultado.get("João")
        );
    }

    private void assertBigDecimalEquals(
            String esperado,
            BigDecimal atual
    ) {

        assertEquals(
                0,
                new BigDecimal(esperado)
                        .compareTo(atual)
        );
    }
}