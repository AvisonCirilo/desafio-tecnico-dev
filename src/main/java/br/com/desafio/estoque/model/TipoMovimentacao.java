package br.com.desafio.estoque.model;

public enum TipoMovimentacao {

    ENTRADA("Entrada de mercadoria"),
    SAIDA("Saída de mercadoria");

    private final String descricao;

    TipoMovimentacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}