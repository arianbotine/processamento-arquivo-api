CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    codigo_usuario BIGINT not null UNIQUE,
    nome VARCHAR(45) NOT NULL
);

CREATE INDEX idx_codigo_usuario ON usuario (codigo_usuario);

CREATE TABLE pedido (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    codigo_pedido BIGINT not null,
    id_produto BIGINT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    data_compra DATE,
    id_usuario BIGSERIAL NOT NULL,
    CONSTRAINT pedido_produto UNIQUE (codigo_pedido, id_produto),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE INDEX idx_codigo_pedido ON pedido (codigo_pedido);
CREATE INDEX idx_data_compra ON pedido (data_compra);