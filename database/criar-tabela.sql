CREATE IF NOT EXISTS TABLE usuario (
    id BIGINT PRIMARY KEY NOT NULL,
    nome VARCHAR(45) NOT NULL
);

CREATE IF NOT EXISTS TABLE pedido (
    id BIGINT PRIMARY KEY NOT NULL,
    data_compra DATE NOT NULL,
    id_usuario BIGINT NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE IF NOT EXISTS INDEX idx_data_compra ON pedido (data_compra);

CREATE IF NOT EXISTS TABLE produto (
    id BIGINT PRIMARY KEY NOT NULL
);

CREATE IF NOT EXISTS TABLE pedido_produto (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    id_pedido BIGINT NOT NULL,
    id_produto BIGINT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    CONSTRAINT unique_pedido_produto UNIQUE (id_pedido, id_produto),
    FOREIGN KEY (id_pedido) REFERENCES pedido(id),
    FOREIGN KEY (id_produto) REFERENCES produto(id)
);

CREATE IF NOT EXISTS INDEX idx_id_pedido ON pedido_produto (id_pedido);
CREATE IF NOT EXISTS INDEX idx_id_produto ON pedido_produto (id_produto);