# Gerenciamento de pedidos API

Esse projeto tem como objetivo estudar conceitos de API REST utilizando Java Spring Boot com consumo de arquivo e processamento de arquivo de texto.

## Índice

- [Contexto](#contexto)
    - [Entrada de dados](#entrada-de-dados)
    - [Saída de dados](#saída-de-dados)
- [Tecnologias](#tecnologias)
- [Solução](#solução)
    - [Modelagem conceitual](#modelagem-conceitual)
    - [Modelagem lógica](#modelagem-lógica)
    - [Modelagem física](#modelagem-física)
- [Arquitetura](#arquitetura)

# Contexto

Foi definido um cenário de negócio ficticio para que possamos trabalhar em uma solução.  
Suponha que temos uma demanda para integrar dois sistemas.  
O sistema legado que possui um arquivo de pedidos desnormalizado e precisamos transformá-lo em um arquivo json normalizado.

## Entrada de dados

O arquivo do sistema legado possui uma estrutura em que cada linha representa uma parte de um
pedido. Os dados estão padronizados por tamanho de seus valores, respeitando a seguinte tabela:

![entrada de dados](assets/schema_entrada_dados.png)

Exemplo (primeira linha não consta no arquivo):

```
|-userId--|--------------userName----------------------|-orderId-|-prodId--|---value---|-date--|
0000000002                                     Medeiros00000123450000000111      256.2420201201
0000000001                                      Zarelli00000001230000000111      512.2420211201
0000000001                                      Zarelli00000001230000000122      512.2420211201
0000000002                                     Medeiros00000123450000000122      256.2420201201
```

Segue dois arquivos de exemplo:



## Saída de dados

Considerando como estrutura base do payload de response o seguinte json:

````json
[
    {
        "user_id": 1,
        "name": "Zarelli",
        "orders": [
            {
                "order_id": 123,
                "total": "1024.48",
                "date": "2021-12-01",
                "products": [
                    {
                        "product_id": 111,
                        "value": "512.24"
                    },
                    {
                        "product_id": 122,
                        "value": "512.24"
                    }
                ]
            }
        ]
    },
    {
        "user_id": 2,
        "name": "Medeiros",
        "orders": [
            {
                "order_id": 12345,
                "total": "512.48",
                "date": "2020-12-01",
                "products": [
                    {
                        "product_id": 111,
                        "value": "256.24"
                    },
                    {
                        "product_id": 122,
                        "value": "256.24"
                    }
                ]
            }
        ]
    }
]
````

Considerar a consulta geral de pedidos e, também, a inclusão dos seguintes filtros:

* id do pedido
* intervalo de data de compra (data início e data fim)

Segue dois arquivos de exemplos que podem ser utilizados:

[dados1](assets/arquivos/data_1.txt)  
[dado2](assets/arquivos/data_2.txt)

## Tecnologias

Tecnologias escolhidas:
* Java Spring Boot (Maven) 
    * Lombok (abstração dos getters e setters e construção de builders)  
    * JPA e Hibernate  
* PostgreSQL (Docker)

A solução proposta define o uso de um banco de dados devido a sua capacidade de indexação e validação dos dados processados pela lógica, otimizando a performance durante a implementação dos filtros e garantindo um ponto de acesso para validações de dados.

## Solução

Foi proposto dois endpoints em uma API, um de POST para receber um arquivo de texto e gravar os registros no banco e outra de GET para buscar os registros no formato json proposto como response.

Segue um detalhamento de como ficou a modelagem dos dados do projeto:

### Modelagem conceitual

![modelagem conceitual](assets/db_modelo_conceitual.jpg)

### Modelagem lógica

![modelagem logica](assets/db_modelo_logico.jpg)

### Modelagem física

![modelagem fisica](assets/db_modelo_fisico.jpg)


## Arquitetura

O projeto foi desenvolvido utilizando uma arquitetura de camadas para separar as regras de negócio do tratamento de dados.

Pacotes:

* `controller`: controladores de api
* `repository`: tratamento de dados
* `service`: regras de negócio e validações

![pacotes](assets/pacotes.png)


## Testes

Além dos testes unitários, foram projetados cenários de testes de retornos de sucesso e de erros esperados para ambos os endpoints (GET e POST), a partir desse documento foi criado uma collection usando o postman para realização dos testes manuais, onde o usuário poderá ter acesso detalhado a todos os tipos de request realizados e responses obtidos.

[casos de testes manuais](docs/test-cases/casos-teste-post-get.xlsx)  
[collection](docs/test-cases/Orders%20Management%20API.postman_collection.json)

Para testar todos os cenários do endpoint POST, foi necessário a manipulação de dados e para isso diversos arquivos foram criados e estão disponiveis em `docs/test-cases/post-teste-arquivos`


## Uso
