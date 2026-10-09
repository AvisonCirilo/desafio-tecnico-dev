package br.com.desafio.vendas;

import br.com.desafio.vendas.io.VendasJsonReader;
import br.com.desafio.vendas.model.Venda;
import br.com.desafio.vendas.service.ComissaoService;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class VendaApplication {

    public static void main(String[] args) {

        VendasJsonReader reader = new VendasJsonReader();
        ComissaoService comissaoService = new ComissaoService();

        try {

            List<Venda> vendas = reader.lerVendas();

            Map<String, BigDecimal> comissoes =
                    comissaoService.calcularComissoesPorVendedor(vendas);

            NumberFormat moeda =
                    NumberFormat.getCurrencyInstance(
                            Locale.forLanguageTag("pt-BR")
                    );

            moeda.setRoundingMode(RoundingMode.HALF_UP);

            System.out.println("=== Comissão por vendedor ===");

            comissoes.forEach((vendedor, comissao) -> {
                System.out.printf(
                        "%-20s %s%n",
                        vendedor,
                        moeda.format(comissao)
                );
            });

        } catch (IOException e) {

            System.err.println(
                    "Erro ao ler o arquivo de vendas: " + e.getMessage()
            );
        }
    }
}