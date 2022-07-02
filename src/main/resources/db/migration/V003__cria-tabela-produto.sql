CREATE TABLE sgc.tb_produto
(
    id        BIGINT auto_increment NOT NULL,
    nome      varchar(100)   NOT NULL,
    descricao varchar(100) NULL,
    preco     DECIMAL(10, 2) NOT NULL,
    ativo     BOOL           NOT NULL,
    CONSTRAINT pk_produto PRIMARY KEY (id)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
