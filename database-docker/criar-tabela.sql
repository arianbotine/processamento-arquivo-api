CREATE TABLE usuario (
    id BIGINT PRIMARY KEY NOT NULL UNIQUE,
    nome VARCHAR(45) NOT NULL
);

CREATE TABLE pedido (
    id BIGINT PRIMARY KEY NOT NULL,
    data_compra DATE NOT NULL,
    id_usuario BIGSERIAL NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE INDEX idx_data_compra ON pedido (data_compra);

CREATE TABLE pedido_produto_valor (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    id_pedido BIGINT NOT NULL,
    id_produto BIGINT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    CONSTRAINT pedido_produto UNIQUE (id_pedido,id_produto),
    FOREIGN KEY (id_pedido) REFERENCES pedido(id)
);

CREATE INDEX idx_id_pedido ON pedido_produto_valor (id_pedido);
CREATE INDEX idx_id_produto ON pedido_produto_valor (id_produto);