package br.com.desafio.estoque.model;

import java.util.UUID;

public class Movimentacao {

    private final UUID id;
    private final Produto produto;
    private final TipoMovimentacao tipo;
    private final int quantidade;

    public Movimentacao(
            Produto produto,
            TipoMovimentacao tipo,
            int quantidade
    ) {
        this.id = UUID.randomUUID();
        this.produto = produto;
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public UUID getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return tipo.getDescricao();
    }
}