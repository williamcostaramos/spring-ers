CREATE TABLE sgc.tb_item_pedido
(
    id             BIGINT auto_increment NOT NULL,
    quantidade     integer        NOT NULL,
    preco_unitario decimal(10, 2) NOT NULL,
    preco_total    decimal(10, 2) NOT NULL,
    observacao     varchar(100) NULL,
    pedido_id      BIGINT         NOT NULL,
    CONSTRAINT pk_item_pedido PRIMARY KEY (id),
    CONSTRAINT fk_tb_pedido FOREIGN KEY (pedido_id) REFERENCES sgc.tb_pedido (id)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
