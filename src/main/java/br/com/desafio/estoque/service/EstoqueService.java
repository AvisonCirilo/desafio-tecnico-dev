package br.com.desafio.estoque.service;

import br.com.desafio.estoque.model.Movimentacao;
import br.com.desafio.estoque.model.Produto;
import br.com.desafio.estoque.model.TipoMovimentacao;

import java.util.List;

public class EstoqueService {

    public Produto buscarProdutoPorCodigo(
            List<Produto> produtos,
            int codigoProduto
    ) {

        for (Produto produto : produtos) {

            if (produto.getCodigoProduto() == codigoProduto) {
                return produto;
            }
        }

        return null;
    }

    public Movimentacao movimentarEstoque(
            Produto produto,
            TipoMovimentacao tipo,
            int quantidade
    ) {

        if (quantidade <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade deve ser maior que zero."
            );
        }

        if (tipo == TipoMovimentacao.SAIDA
                && quantidade > produto.getEstoque()) {

            throw new IllegalArgumentException(
                    "Estoque insuficiente para realizar a saída."
            );
        }

        if (tipo == TipoMovimentacao.ENTRADA) {

            produto.setEstoque(
                    produto.getEstoque() + quantidade
            );

        } else {

            produto.setEstoque(
                    produto.getEstoque() - quantidade
            );
        }

        return new Movimentacao(
                produto,
                tipo,
                quantidade
        );
    }
}