# Consulta de CNPJ

Este projeto é um aplicativo Java que consulta informações sobre CNPJs utilizando a API demonstração do Serpro e exibe os resultados em uma interface gráfica Swing. 

## Funcionalidades

- Consulta de CNPJ
- Validação de entrada de CNPJ
- Exibição dos dados do CNPJ em uma área de texto

## Requisitos

- Java 8 ou superior
- Biblioteca `Gson` para manipulação de JSON
- Biblioteca `iText` para exportação de PDF

## Configuração do Projeto

1. Clone este repositório para a sua máquina local:

    ```sh
    git clone https://github.com/seu-usuario/consulta-cnpj.git
    cd consulta-cnpj
    ```

2. Adicione a biblioteca `Gson` ao seu projeto. Se você estiver usando o Maven, adicione as seguintes dependências ao seu `pom.xml`:

    ```xml
    <dependencies>
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.8.8</version>
        </dependency>
    </dependencies>
    ```

3. Compile e execute o projeto utilizando sua IDE favorita ou via linha de comando:

    ```sh
    javac -cp .;path/to/gson.jar;path/to/itextpdf.jar com/consulta/cnpj/*.java
    java -cp .;path/to/gson.jar;path/to/itextpdf.jar com.consulta.cnpj.Principal
    ```

## Uso

1. Abra o aplicativo.
2. Digite o CNPJ no campo de texto.
3. Clique no botão "Consultar".
4. Os resultados da consulta serão exibidos na área de texto.

## Estrutura do Projeto

```plaintext
consulta-cnpj/
├── src/
│   └── com/
│       └── consulta/
│           └── cnpj/
│               ├── CnpjConsulta.java
│               └── Principal.java
├── README.md
└── pom.xml (opcional, se você estiver usando o Maven)

