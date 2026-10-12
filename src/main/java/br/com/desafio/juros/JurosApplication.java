package br.com.desafio.juros;

import br.com.desafio.juros.service.JurosService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

public class JurosApplication {

    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        JurosService jurosService = new JurosService();

        try {

            System.out.println("=== CÁLCULO DE JUROS ===");

            BigDecimal valor =
                    lerValor(scanner);

            LocalDate vencimento =
                    lerData(scanner);

            BigDecimal juros =
                    jurosService.calcularJuros(
                            valor,
                            vencimento
                    );

            long diasAtraso =
                    jurosService.calcularDiasAtraso(
                            vencimento
                    );

            BigDecimal valorAtualizado =
                    valor.add(juros);

            NumberFormat moeda =
                    NumberFormat.getCurrencyInstance(
                            Locale.forLanguageTag("pt-BR")
                    );

            moeda.setRoundingMode(
                    RoundingMode.HALF_UP
            );

            System.out.println();
            System.out.println("=== RESULTADO ===");

            System.out.println(
                    "Valor original: "
                            + moeda.format(valor)
            );

            System.out.println(
                    "Data de vencimento: "
                            + vencimento.format(FORMATO_DATA)
            );

            System.out.println(
                    "Dias em atraso: "
                            + diasAtraso
            );

            System.out.println(
                    "Juros: "
                            + moeda.format(juros)
            );

            System.out.println(
                    "Valor atualizado: "
                            + moeda.format(valorAtualizado)
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "\nNão foi possível realizar o cálculo."
            );

            System.out.println(
                    "Motivo: " + e.getMessage()
            );

        } finally {
            scanner.close();
        }
    }

    private static BigDecimal lerValor(
            Scanner scanner
    ) {

        while (true) {

            System.out.print(
                    "Informe o valor: R$ "
            );

            String entrada =
                    scanner.nextLine()
                            .trim()
                            .replace(",", ".");

            try {

                return new BigDecimal(entrada);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Digite um valor válido."
                );
            }
        }
    }

    private static LocalDate lerData(
            Scanner scanner
    ) {

        while (true) {

            System.out.print(
                    "Informe a data de vencimento (dd/MM/yyyy): "
            );

            String entrada =
                    scanner.nextLine().trim();

            try {

                return LocalDate.parse(
                        entrada,
                        FORMATO_DATA
                );

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Digite uma data válida no formato dd/MM/yyyy."
                );
            }
        }
    }
}