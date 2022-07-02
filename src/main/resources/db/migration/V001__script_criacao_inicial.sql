CREATE TABLE `tb_cozinha`
(
    `id`   bigint                                  NOT NULL AUTO_INCREMENT,
    `nome` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB   CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;




CREATE TABLE `tb_usuario`
(
    `id`    bigint                                  NOT NULL AUTO_INCREMENT,
    `nome`  varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
    `senha` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
    `tipo`  int                                     NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB  CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tb_grupo`
(
    `id`        bigint                                  NOT NULL AUTO_INCREMENT,
    `nome`      varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
    `descricao` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB  CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tb_usuario_grupo`
(
    `id_tb_usuario` bigint NOT NULL,
    `id_tb_grupo`   bigint NOT NULL,
    KEY             `fk_grupo_id` (`id_tb_grupo`),
    KEY             `fk_usuario_id` (`id_tb_usuario`),
    CONSTRAINT `fk_grupo_id` FOREIGN KEY (`id_tb_grupo`) REFERENCES `tb_grupo` (`id`),
    CONSTRAINT `fk_usuario_id` FOREIGN KEY (`id_tb_usuario`) REFERENCES `tb_usuario` (`id`)
) ENGINE=InnoDB  CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `tb_estado`
(
    `id`   bigint                                  NOT NULL AUTO_INCREMENT,
    `nome` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
    `uf`   varchar(2) COLLATE utf8mb4_general_ci   NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB  CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tb_cidade`
(
    `id`        bigint                                  NOT NULL,
    `nome`      varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
    `id_estado` bigint                                  NOT NULL,
    PRIMARY KEY (`id`),
    KEY         `fk_estado_id` (`id_estado`),
    CONSTRAINT `fk_estado_id` FOREIGN KEY (`id_estado`) REFERENCES `tb_estado` (`id`)
) ENGINE=InnoDB  CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tb_restaurante`
(
    `id`                   bigint                                  NOT NULL AUTO_INCREMENT,
    `nome`                 varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
    `taxa_frete`           decimal(10, 0)                          NOT NULL,
    `cozinha_id`           bigint NOT NULL DEFAULT '1',
    `endereco_cep`         varchar(12) COLLATE utf8mb4_general_ci  DEFAULT NULL,
    `endereco_logradouro`  varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `endereco_complemento` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `endereco_bairro`      varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `cidade_id`            bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                    `fk_cozinha_id` (`cozinha_id`),
    KEY                    `fk_cidade_id` (`cidade_id`),
    CONSTRAINT `fk_cozinha_id` FOREIGN KEY (`cidade_id`) REFERENCES `tb_cidade` (`id`),
    CONSTRAINT `fk_cidade_id` FOREIGN KEY (`cozinha_id`) REFERENCES `tb_cozinha` (`id`)
) ENGINE=InnoDB  CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `tb_forma_pagamento`
(
    `id`        bigint                                  NOT NULL AUTO_INCREMENT,
    `descricao` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tb_restaurante_forma_pagamento`
(
    `restaurante_id`     bigint NOT NULL,
    `forma_pagamento_id` bigint NOT NULL,
    KEY                  `fk_restaurante_id` (`restaurante_id`),
    KEY                  `fk_forma_pagemento_id` (`forma_pagamento_id`),
    CONSTRAINT `fk_restaurante_id` FOREIGN KEY (`restaurante_id`) REFERENCES `tb_restaurante` (`id`),
    CONSTRAINT `fk_forma_pagemento_id` FOREIGN KEY (`forma_pagamento_id`) REFERENCES `tb_forma_pagamento` (`id`)
) ENGINE=InnoDB  CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;