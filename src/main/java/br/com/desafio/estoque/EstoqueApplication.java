package br.com.desafio.estoque;

import br.com.desafio.estoque.io.EstoqueJsonReader;
import br.com.desafio.estoque.model.Movimentacao;
import br.com.desafio.estoque.model.Produto;
import br.com.desafio.estoque.model.TipoMovimentacao;
import br.com.desafio.estoque.service.EstoqueService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class EstoqueApplication {

    public static void main(String[] args) {

        EstoqueJsonReader reader = new EstoqueJsonReader();
        EstoqueService estoqueService = new EstoqueService();
        Scanner scanner = new Scanner(System.in);

        try {

            List<Produto> produtos = reader.lerEstoque();

            int opcao;

            do {

                exibirMenu();

                opcao = lerInteiro(
                        scanner,
                        "Escolha uma opção: "
                );

                switch (opcao) {

                    case 1 -> realizarMovimentacao(
                            scanner,
                            produtos,
                            estoqueService,
                            TipoMovimentacao.ENTRADA
                    );

                    case 2 -> realizarMovimentacao(
                            scanner,
                            produtos,
                            estoqueService,
                            TipoMovimentacao.SAIDA
                    );

                    case 3 -> exibirProdutos(produtos);

                    case 0 -> System.out.println(
                            "\nSistema encerrado."
                    );

                    default -> System.out.println(
                            "\nOpção inválida."
                    );
                }

            } while (opcao != 0);

        } catch (IOException e) {

            System.err.println(
                    "Erro ao carregar o estoque: "
                            + e.getMessage()
            );

        } finally {
            scanner.close();
        }
    }

    private static void exibirMenu() {

        System.out.println();
        System.out.println("=== CONTROLE DE ESTOQUE ===");
        System.out.println("1 - Entrada de mercadoria");
        System.out.println("2 - Saída de mercadoria");
        System.out.println("3 - Consultar estoque");
        System.out.println("0 - Sair");
        System.out.println();
    }

    private static void exibirProdutos(
            List<Produto> produtos
    ) {

        System.out.println();
        System.out.println("=== PRODUTOS EM ESTOQUE ===");

        for (Produto produto : produtos) {

            System.out.printf(
                    "%d - %-30s | Estoque: %d%n",
                    produto.getCodigoProduto(),
                    produto.getDescricaoProduto(),
                    produto.getEstoque()
            );
        }
    }

    private static void realizarMovimentacao(
            Scanner scanner,
            List<Produto> produtos,
            EstoqueService estoqueService,
            TipoMovimentacao tipo
    ) {

        exibirProdutos(produtos);

        int codigoProduto = lerInteiro(
                scanner,
                "\nInforme o código do produto: "
        );

        Produto produto =
                estoqueService.buscarProdutoPorCodigo(
                        produtos,
                        codigoProduto
                );

        if (produto == null) {

            System.out.println(
                    "\nProduto não encontrado."
            );

            return;
        }

        int quantidade = lerInteiro(
                scanner,
                "Informe a quantidade: "
        );

        try {

            Movimentacao movimentacao =
                    estoqueService.movimentarEstoque(
                            produto,
                            tipo,
                            quantidade
                    );

            System.out.println();
            System.out.println(
                    "Movimentação realizada com sucesso."
            );

            System.out.println(
                    "ID: " + movimentacao.getId()
            );

            System.out.println(
                    "Tipo: " + movimentacao.getDescricao()
            );

            System.out.println(
                    "Produto: "
                            + produto.getDescricaoProduto()
            );

            System.out.println(
                    "Quantidade movimentada: "
                            + movimentacao.getQuantidade()
            );

            System.out.println(
                    "Estoque final: "
                            + movimentacao.getEstoqueFinal()
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "\nNão foi possível realizar a movimentação."
            );

            System.out.println(
                    "Motivo: " + e.getMessage()
            );
        }
    }

    private static int lerInteiro(
            Scanner scanner,
            String mensagem
    ) {

        while (true) {

            System.out.print(mensagem);

            String entrada = scanner.nextLine();

            try {

                return Integer.parseInt(
                        entrada.trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Digite um número válido."
                );
            }
        }
    }
}