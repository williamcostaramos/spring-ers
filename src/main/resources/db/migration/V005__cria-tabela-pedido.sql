CREATE TABLE sgc.tb_pedido
(
    id                   BIGINT auto_increment NOT NULL,
    subtotal             DECIMAL(10, 2) NOT NULL,
    taxa_frete           decimal(10, 2) NOT NULL,
    data_criacao         TIMESTAMP      NOT NULL,
    data_confirmacao     TIMESTAMP NULL,
    data_cancelamento    TIMESTAMP NULL,
    data_entrega         TIMESTAMP NULL,
    status               integer        NOT NULL,
    endereco_cep         varchar(8)     NOT NULL,
    endereco_logradouro  varchar(100)   NOT NULL,
    endereco_numero      varchar(20)    NOT NULL,
    endereco_bairro      varchar(100)   NOT NULL,
    endereco_complemento varchar(100) NULL,
    forma_pagamento_id   BIGINT         NOT NULL,
    restaurante_id       BIGINT         NOT NULL,
    usuario_cliente_id   BIGINT         NOT NULL,
    CONSTRAINT pk_pedido PRIMARY KEY (id),
    CONSTRAINT fk_tb_forma_pagamento FOREIGN KEY (forma_pagamento_id) REFERENCES sgc.tb_forma_pagamento (id),
    CONSTRAINT fk_tb_restaurante FOREIGN KEY (restaurante_id) REFERENCES sgc.tb_restaurante (id),
    CONSTRAINT fk_tb_usuario FOREIGN KEY (usuario_cliente_id) REFERENCES sgc.tb_usuario (id)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
