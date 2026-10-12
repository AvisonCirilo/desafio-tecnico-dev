package br.com.desafio.estoque.model;

import java.util.List;

public class EstoqueData {

    private List<Produto> estoque;

    public EstoqueData() {
    }

    public List<Produto> getEstoque() {
        return estoque;
    }

    public void setEstoque(List<Produto> estoque) {
        this.estoque = estoque;
    }
}