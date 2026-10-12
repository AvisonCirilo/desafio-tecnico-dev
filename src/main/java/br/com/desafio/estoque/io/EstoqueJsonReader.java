package br.com.desafio.estoque.io;

import br.com.desafio.estoque.model.EstoqueData;
import br.com.desafio.estoque.model.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EstoqueJsonReader {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Produto> lerEstoque() throws IOException {

        InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("estoque.json");

        if (inputStream == null) {
            throw new FileNotFoundException(
                    "Arquivo estoque.json não encontrado."
            );
        }

        try (inputStream) {

            EstoqueData estoqueData =
                    objectMapper.readValue(inputStream, EstoqueData.class);

            return estoqueData.getEstoque();
        }
    }
}