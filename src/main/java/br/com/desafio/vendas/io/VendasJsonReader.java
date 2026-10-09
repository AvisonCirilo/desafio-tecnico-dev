package br.com.desafio.vendas.io;

import br.com.desafio.vendas.model.Venda;
import br.com.desafio.vendas.model.VendasData;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class VendasJsonReader {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Venda> lerVendas() throws IOException {

        InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("vendas.json");

        if (inputStream == null) {
            throw new FileNotFoundException(
                    "Arquivo vendas.json não encontrado."
            );
        }

        try (inputStream) {
            VendasData vendasData =
                    objectMapper.readValue(inputStream, VendasData.class);

            return vendasData.getVendas();
        }
    }
}