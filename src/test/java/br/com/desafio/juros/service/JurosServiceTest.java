package br.com.desafio.juros.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class JurosServiceTest {

    private JurosService service;

    @BeforeEach
    void setUp() {

        Clock clock =
                Clock.fixed(
                        Instant.parse(
                                "2026-10-12T00:00:00Z"
                        ),
                        ZoneOffset.UTC
                );

        service = new JurosService(clock);
    }

    @Test
    void deveCalcularJurosPorDiasDeAtraso() {

        BigDecimal juros =
                service.calcularJuros(
                        new BigDecimal("1000.00"),
                        LocalDate.of(
                                2026,
                                10,
                                1
                        )
                );

        assertEquals(
                0,
                new BigDecimal("275.00")
                        .compareTo(juros)
        );
    }

    @Test
    void naoDeveCobrarJurosAntesDoVencimento() {

        BigDecimal juros =
                service.calcularJuros(
                        new BigDecimal("1000.00"),
                        LocalDate.of(
                                2026,
                                10,
                                20
                        )
                );

        assertEquals(
                0,
                BigDecimal.ZERO.compareTo(juros)
        );
    }

    @Test
    void naoDeveCobrarJurosNoDiaDoVencimento() {

        BigDecimal juros =
                service.calcularJuros(
                        new BigDecimal("1000.00"),
                        LocalDate.of(
                                2026,
                                10,
                                12
                        )
                );

        assertEquals(
                0,
                BigDecimal.ZERO.compareTo(juros)
        );
    }

    @Test
    void deveRejeitarValorNegativo() {

        assertThrows(
                IllegalArgumentException.class,
                () -> service.calcularJuros(
                        new BigDecimal("-100"),
                        LocalDate.of(
                                2026,
                                10,
                                1
                        )
                )
        );
    }
}