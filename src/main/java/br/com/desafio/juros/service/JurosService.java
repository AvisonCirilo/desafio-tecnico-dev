package br.com.desafio.juros.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class JurosService {

    private static final BigDecimal TAXA_DIARIA =
            new BigDecimal("0.025");

    public BigDecimal calcularJuros(
            BigDecimal valor,
            LocalDate dataVencimento
    ) {

        validarDados(valor, dataVencimento);

        LocalDate hoje = LocalDate.now();

        long diasAtraso =
                ChronoUnit.DAYS.between(
                        dataVencimento,
                        hoje
                );

        if (diasAtraso <= 0) {
            return BigDecimal.ZERO;
        }

        return valor
                .multiply(TAXA_DIARIA)
                .multiply(BigDecimal.valueOf(diasAtraso));
    }

    public long calcularDiasAtraso(
            LocalDate dataVencimento
    ) {

        LocalDate hoje = LocalDate.now();

        long dias =
                ChronoUnit.DAYS.between(
                        dataVencimento,
                        hoje
                );

        return Math.max(dias, 0);
    }

    private void validarDados(
            BigDecimal valor,
            LocalDate dataVencimento
    ) {

        if (valor == null) {
            throw new IllegalArgumentException(
                    "O valor não pode ser nulo."
            );
        }

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "O valor deve ser maior que zero."
            );
        }

        if (dataVencimento == null) {
            throw new IllegalArgumentException(
                    "A data de vencimento não pode ser nula."
            );
        }
    }
}