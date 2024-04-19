CREATE TABLE usuario (
    id BIGSERIAL CONSTRAINT pk_id_usuario PRIMARY KEY,
    nome VARCHAR(45) NOT NULL
);

CREATE TABLE pedido (
    id BIGSERIAL PRIMARY KEY,
    id_produto BIGINT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    data_compra DATE,
    id_usuario BIGSERIAL NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE INDEX idx_data_compra ON pedido (data_compra);