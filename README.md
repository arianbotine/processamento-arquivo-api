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

service: regras de negócio e validações
repository: tratamento de dados
controller: controladores de api

![alt text](assets/pacotes.png)
