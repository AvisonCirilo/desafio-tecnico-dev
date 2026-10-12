# Desafio Técnico - Java

Projeto desenvolvido como solução para um desafio técnico composto por três exercícios envolvendo **processamento de vendas**, **controle de estoque** e **cálculo de juros**.

A proposta da implementação foi atender aos requisitos de forma simples e organizada, mantendo as responsabilidades das classes separadas e adicionando validações para alguns cenários relevantes.

---

## Tecnologias utilizadas

- Java 21
- Maven
- Jackson
- JUnit 5
- Git

---

## Estrutura do desafio

O projeto foi dividido em três módulos lógicos independentes:

1. Comissão de vendas
2. Controle de estoque
3. Cálculo de juros

Cada módulo possui sua própria classe de execução e, quando necessário, classes específicas para leitura de dados, modelos e regras de negócio.

---

# 1. Comissão de vendas

O programa realiza a leitura das vendas a partir do arquivo `vendas.json` e calcula a comissão acumulada de cada vendedor.

## Regras de comissão

| Valor da venda | Comissão |
|---|---:|
| Abaixo de R$ 100,00 | Sem comissão |
| De R$ 100,00 até R$ 499,99 | 1% |
| A partir de R$ 500,00 | 5% |

A regra é aplicada individualmente a cada venda e, posteriormente, as comissões são agrupadas por vendedor.

Os valores monetários são manipulados utilizando `BigDecimal`, evitando possíveis problemas de precisão associados ao uso de `double`.

### Fluxo

```text
vendas.json
     ↓
VendasJsonReader
     ↓
List<Venda>
     ↓
ComissaoService
     ↓
Comissão acumulada por vendedor
     ↓
VendaApplication
```

A aplicação exibe o resultado formatado em Real Brasileiro (`pt-BR`).

O cálculo é mantido com sua precisão original durante o processamento e o arredondamento monetário ocorre na apresentação do resultado.

---

# 2. Controle de estoque

O programa permite realizar movimentações de **entrada** e **saída** dos produtos fornecidos no arquivo `estoque.json`.

Cada movimentação contém:

- identificador único;
- produto movimentado;
- tipo da movimentação;
- quantidade movimentada;
- estoque final do produto.

O identificador das movimentações é gerado utilizando `UUID`.

## Tipos de movimentação

Os tipos são representados através de um `enum`:

```text
ENTRADA
SAIDA
```

Isso evita a utilização de valores textuais inconsistentes ao longo da aplicação.

## Validações

A aplicação impede:

- movimentações com quantidade igual ou inferior a zero;
- saída de quantidade superior ao estoque disponível;
- seleção de produtos inexistentes;
- valores não numéricos nas opções que esperam números.

Exemplo de uma movimentação:

```text
Produto: Caneta Azul
Estoque inicial: 150
Tipo: Entrada
Quantidade: 50
Estoque final: 200
```

### Fluxo

```text
estoque.json
      ↓
EstoqueJsonReader
      ↓
List<Produto>
      ↓
EstoqueService
      ↓
Movimentação
      ↓
EstoqueApplication
```

A aplicação possui um menu interativo pelo terminal:

```text
=== CONTROLE DE ESTOQUE ===

1 - Entrada de mercadoria
2 - Saída de mercadoria
3 - Consultar estoque
0 - Sair
```

> As alterações realizadas no estoque são mantidas em memória durante a execução da aplicação.
>
> Ao reiniciar o programa, os valores originais são carregados novamente a partir do arquivo `estoque.json`.

A persistência permanente das movimentações não faz parte da implementação atual.

---

# 3. Cálculo de juros

O terceiro exercício calcula os juros de um valor considerando:

- valor original;
- data de vencimento;
- data atual;
- taxa de 2,5% ao dia.

Foi adotado o cálculo de **juros simples**, utilizando:

```text
juros = valor × 0,025 × dias de atraso
```

Caso a data de vencimento seja igual ou posterior à data atual, o valor dos juros será zero.

A aplicação também apresenta o valor atualizado:

```text
valor atualizado = valor original + juros
```

## Tratamento de datas

As datas são manipuladas utilizando `LocalDate`.

A diferença em dias é calculada através de:

```text
ChronoUnit.DAYS
```

O `JurosService` utiliza um `Clock` injetável.

Na execução normal é utilizado o relógio do sistema, mas durante os testes é possível utilizar uma data fixa.

Isso torna os testes reproduzíveis e independentes do dia em que são executados.

### Exemplo

Considerando:

```text
Valor: R$ 1.000,00
Vencimento: 01/10/2026
Data atual: 12/10/2026
```

Temos:

```text
Dias em atraso: 11

Juros:
1000 × 0,025 × 11

Juros = R$ 275,00

Valor atualizado = R$ 1.275,00
```

> O enunciado informa a taxa diária de 2,5%, mas não especifica o regime de capitalização.
>
> Por esse motivo, nesta implementação foi adotado o cálculo de juros simples.

---

# Organização do projeto

```text
src/
├── main/
│   ├── java/
│   │   └── br/com/desafio/
│   │       ├── vendas/
│   │       │   ├── io/
│   │       │   ├── model/
│   │       │   ├── service/
│   │       │   └── VendaApplication.java
│   │       │
│   │       ├── estoque/
│   │       │   ├── io/
│   │       │   ├── model/
│   │       │   ├── service/
│   │       │   └── EstoqueApplication.java
│   │       │
│   │       └── juros/
│   │           ├── service/
│   │           └── JurosApplication.java
│   │
│   └── resources/
│       ├── vendas.json
│       └── estoque.json
│
└── test/
    └── java/
        └── br/com/desafio/
            ├── vendas/
            │   ├── io/
            │   │   └── VendasJsonReaderTest.java
            │   └── service/
            │       └── ComissaoServiceTest.java
            │
            ├── estoque/
            │   ├── io/
            │   │   └── EstoqueJsonReaderTest.java
            │   └── service/
            │       └── EstoqueServiceTest.java
            │
            └── juros/
                └── service/
                    └── JurosServiceTest.java
```

---

# Requisitos

Para executar o projeto é necessário possuir:

- Java 21 ou superior
- Apache Maven
- Git

Para conferir a versão do Java:

```bash
java -version
```

Para conferir a versão do Maven:

```bash
mvn -version
```

---

# Como executar

Clone o repositório:

```bash
git clone https://github.com/AvisonCirilo/desafio-tecnico-dev.git
```

Entre no diretório:

```bash
cd desafio-tecnico-dev
```

Compile o projeto:

```bash
mvn clean compile
```

As três aplicações possuem classes `main` independentes:

```text
VendaApplication
EstoqueApplication
JurosApplication
```

Elas podem ser executadas diretamente através de uma IDE Java, como o IntelliJ IDEA.

---

# Testes

Os testes automatizados foram desenvolvidos utilizando **JUnit 5**.

Para executá-los:

```bash
mvn test
```

Os testes verificam cenários importantes das regras de negócio.

Também existem testes de leitura dos arquivos JSON, garantindo que os dados de vendas e estoque sejam carregados corretamente através do Jackson.

## Comissão

São verificados principalmente os limites das faixas:

```text
R$ 99,99  → sem comissão
R$ 100,00 → 1%
R$ 499,99 → 1%
R$ 500,00 → 5%
```

Também é verificado o agrupamento das comissões por vendedor.

Esses casos foram escolhidos por representarem os pontos de transição das regras de comissão.

## Estoque

São testados cenários como:

- entrada de produtos;
- saída de produtos;
- geração do identificador da movimentação;
- tentativa de saída maior que o estoque disponível;
- movimentação com quantidade zero;
- leitura dos produtos a partir do arquivo `estoque.json`.

## Vendas

Também é verificada a leitura das vendas a partir do arquivo `vendas.json`.

## Juros

São testados cenários como:

- título vencido;
- título ainda não vencido;
- vencimento na data atual;
- valor negativo.

Nos testes de juros é utilizado um `Clock` fixo para que o resultado não dependa da data em que os testes forem executados.

---

# Decisões técnicas

## BigDecimal

Os valores monetários são representados através de `BigDecimal`.

Essa escolha evita problemas de precisão que podem ocorrer com tipos de ponto flutuante, como `double`.

---

## LocalDate

Foi utilizado `LocalDate` porque os cálculos do desafio dependem apenas de datas e não necessitam de informações de horário.

---

## Clock

O serviço responsável pelo cálculo de juros recebe um `Clock`.

Na aplicação é utilizado:

```text
Clock.systemDefaultZone()
```

Enquanto nos testes é possível utilizar:

```text
Clock.fixed(...)
```

Isso permite controlar a data utilizada durante um teste.

---

## Enum

As movimentações de estoque são representadas através do `TipoMovimentacao`.

Os valores possíveis são:

```text
ENTRADA
SAIDA
```

Assim, o sistema evita strings inconsistentes para representar os tipos de movimentação.

---

## UUID

Cada movimentação de estoque recebe um `UUID`.

Exemplo:

```text
42fc234a-99c4-483b-b75e-74e4f4ea20b7
```

Dessa forma, cada movimentação possui um identificador próprio sem necessidade de banco de dados.

---

## Leitura dos arquivos JSON

Os arquivos JSON são armazenados em:

```text
src/main/resources
```

e carregados através do classpath da aplicação.

A conversão entre JSON e objetos Java é realizada utilizando a biblioteca Jackson.

---

# Tratamento de erros

Além das regras solicitadas pelo desafio, foram incluídas algumas validações para evitar estados inconsistentes.

Entre elas:

- arquivo JSON não encontrado;
- produto inexistente;
- estoque insuficiente;
- quantidade igual ou inferior a zero;
- entrada não numérica;
- data inválida;
- valor monetário inválido ou negativo.

O objetivo dessas validações é evitar que uma entrada incorreta encerre a aplicação inesperadamente ou produza dados inconsistentes.

---

# Possíveis evoluções

Caso o projeto evoluísse para uma aplicação maior, algumas melhorias possíveis seriam:

- persistência permanente das movimentações;
- armazenamento em banco de dados;
- histórico de movimentações de estoque;
- API REST para disponibilização das funcionalidades;
- interface web ou desktop;
- logging estruturado;
- configuração externa das taxas de comissão;
- configuração externa da taxa de juros.

Essas funcionalidades não foram adicionadas nesta versão para manter a implementação proporcional ao escopo solicitado pelo desafio.

---

# Considerações finais

A solução foi desenvolvida buscando equilibrar:

- simplicidade;
- legibilidade;
- separação de responsabilidades;
- validação das regras de negócio;
- testabilidade;
- facilidade de manutenção.

A intenção foi evitar complexidade desnecessária e manter cada exercício independente e fácil de compreender.

---

## Autor

**Avison Cirilo**

Desenvolvido como parte de um desafio técnico para uma vaga de desenvolvimento.