# Desafio Técnico - Java

Projeto desenvolvido como solução para um desafio técnico composto por três exercícios envolvendo processamento de vendas, controle de estoque e cálculo de juros.

O objetivo foi implementar as regras propostas de forma simples, legível e organizada, mantendo responsabilidades separadas e adicionando validações para cenários relevantes.

## Tecnologias

- Java 21
- Maven
- Jackson
- JUnit 5
- Git

## Estrutura

O projeto foi dividido em três módulos lógicos:

## 1. Comissão de vendas

O programa realiza a leitura das vendas a partir de um arquivo JSON e calcula a comissão acumulada de cada vendedor.

### Regras de comissão

| Valor da venda                 | Comissão |
|--------------------------------|---:|
| Abaixo de R$ 100,00            | Sem comissão |
| De R$ 100,00 - até - R$ 499,99 | 1% |
| A partir de R$ 500,00          | 5% |

Os valores monetários são manipulados com `BigDecimal`, evitando problemas de precisão associados ao uso de `double`.
## 2. Controle de estoque

O programa permite realizar movimentações de entrada e saída dos produtos fornecidos no arquivo JSON.

### Informações de cada movimentação

- Identificador único (`UUID`)
- Produto movimentado
- Tipo da movimentação
- Quantidade movimentada
- Estoque final

### Validações

A aplicação impede:

- movimentações com quantidade igual ou inferior a zero;
- saída de quantidade superior ao estoque disponível;
- seleção de produtos inexistentes.

> As alterações são mantidas em memória durante a execução da aplicação.  
> A persistência permanente pode ser adicionada como uma evolução futura.

### 3. Cálculo de juros

Calcula o valor dos juros de um título a partir:

- do valor original;
- da data de vencimento;
- da data atual.

Foi considerada a taxa de 2,5% ao dia utilizando juros simples:

`juros = valor × 0,025 × dias de atraso`

Para títulos ainda não vencidos, o valor dos juros é zero.

## Organização do projeto

```text
src/
├── main/
│   ├── java/br/com/desafio/
│   │   ├── vendas/
│   │   ├── estoque/
│   │   └── juros/
│   └── resources/
│       ├── vendas.json
│       └── estoque.json
│
└── test/
    └── java/br/com/desafio/
        ├── vendas/
        ├── estoque/
        └── juros/