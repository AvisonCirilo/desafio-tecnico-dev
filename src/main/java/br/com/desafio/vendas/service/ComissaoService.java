package br.com.desafio.vendas.service;

import br.com.desafio.vendas.model.Venda;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

public class ComissaoService {

    private static final BigDecimal LIMITE_SEM_COMISSAO = new BigDecimal("100.00");
    private static final BigDecimal LIMITE_COMISSAO_UM_PORCENTO = new BigDecimal("500.00");

    private static final BigDecimal COMISSAO_UM_PORCENTO = new BigDecimal("0.01");
    private static final BigDecimal COMISSAO_CINCO_PORCENTO = new BigDecimal("0.05");

    public BigDecimal calcularComissao(Venda venda) {
        BigDecimal valor = venda.getValor();

        if (valor.compareTo(LIMITE_SEM_COMISSAO) < 0) {
            return BigDecimal.ZERO;
        }

        if (valor.compareTo(LIMITE_COMISSAO_UM_PORCENTO) < 0) {
            return valor.multiply(COMISSAO_UM_PORCENTO);
        }

        return valor.multiply(COMISSAO_CINCO_PORCENTO);
    }

    public Map<String, BigDecimal> calcularComissoesPorVendedor(List<Venda> vendas) {

        Map<String, BigDecimal> comissoes = new LinkedHashMap<>();

        for (Venda venda : vendas) {

            BigDecimal comissaoVenda = calcularComissao(venda);

            comissoes.merge(
                    venda.getVendedor(),
                    comissaoVenda,
                    BigDecimal::add
            );
        }

        return comissoes;
    }
}