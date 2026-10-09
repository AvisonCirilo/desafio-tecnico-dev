package br.com.desafio.vendas.model;

import java.util.List;

public class VendasData {

    private List<Venda> vendas;

    public VendasData() {
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
}