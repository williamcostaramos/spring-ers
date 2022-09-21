CREATE TABLE `tb_cozinha` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tb_estado` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `uf` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tb_grupo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `descricao` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- sgc.tb_cidade definition

CREATE TABLE `tb_cidade` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `id_estado` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_estado_id` (`id_estado`),
  CONSTRAINT `fk_estado_id` FOREIGN KEY (`id_estado`) REFERENCES `tb_estado` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tb_forma_pagamento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tb_permissao` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `descricao` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tb_restaurante` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `taxa_frete` decimal(10,0) NOT NULL,
  `cozinha_id` bigint NOT NULL DEFAULT '1',
  `endereco_cep` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `endereco_logradouro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `endereco_complemento` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `endereco_bairro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `cidade_id` bigint DEFAULT NULL,
  `ativo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_cozinha_id` (`cozinha_id`),
  KEY `fk_cidade_id` (`cidade_id`),
  CONSTRAINT `fk_restaurante_cidade_id` FOREIGN KEY (`cidade_id`) REFERENCES `tb_cidade` (`id`),
  CONSTRAINT `fk_restaurante_cozinha_id` FOREIGN KEY (`cozinha_id`) REFERENCES `tb_cozinha` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `tb_restaurante_forma_pagamento` (
  `restaurante_id` bigint NOT NULL,
  `forma_pagamento_id` bigint NOT NULL,
  KEY `fk_restaurante_id` (`restaurante_id`),
  KEY `fk_forma_pagemento_id` (`forma_pagamento_id`),
  CONSTRAINT `fk_forma_pagemento_id` FOREIGN KEY (`forma_pagamento_id`) REFERENCES `tb_forma_pagamento` (`id`),
  CONSTRAINT `fk_restaurante_id` FOREIGN KEY (`restaurante_id`) REFERENCES `tb_restaurante` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `tb_usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `senha` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `tipo` int NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `ativo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `tb_usuario_grupo` (
  `id_tb_usuario` bigint NOT NULL,
  `id_tb_grupo` bigint NOT NULL,
  KEY `fk_grupo_id` (`id_tb_grupo`),
  KEY `fk_usuario_id` (`id_tb_usuario`),
  CONSTRAINT `fk_grupo_id` FOREIGN KEY (`id_tb_grupo`) REFERENCES `tb_grupo` (`id`),
  CONSTRAINT `fk_usuario_id` FOREIGN KEY (`id_tb_usuario`) REFERENCES `tb_usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `tb_produto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `descricao` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `preco` decimal(10,2) NOT NULL,
  `ativo` tinyint(1) NOT NULL,
  `restaurante_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tb_produto_FK` (`restaurante_id`),
  CONSTRAINT `tb_produto_FK` FOREIGN KEY (`restaurante_id`) REFERENCES `tb_restaurante` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `tb_pedido` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `subtotal` decimal(10,2) NOT NULL,
  `taxa_frete` decimal(10,2) NOT NULL,
  `data_criacao` timestamp NOT NULL,
  `data_confirmacao` timestamp NULL DEFAULT NULL,
  `data_cancelamento` timestamp NULL DEFAULT NULL,
  `data_entrega` timestamp NULL DEFAULT NULL,
  `status` int NOT NULL,
  `endereco_cep` varchar(8) COLLATE utf8mb4_general_ci NOT NULL,
  `endereco_logradouro` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `endereco_numero` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `endereco_bairro` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `endereco_complemento` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `forma_pagamento_id` bigint NOT NULL,
  `restaurante_id` bigint NOT NULL,
  `usuario_cliente_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tb_forma_pagamento` (`forma_pagamento_id`),
  KEY `fk_tb_restaurante` (`restaurante_id`),
  KEY `fk_tb_usuario` (`usuario_cliente_id`),
  CONSTRAINT `fk_tb_forma_pagamento` FOREIGN KEY (`forma_pagamento_id`) REFERENCES `tb_forma_pagamento` (`id`),
  CONSTRAINT `fk_tb_restaurante` FOREIGN KEY (`restaurante_id`) REFERENCES `tb_restaurante` (`id`),
  CONSTRAINT `fk_tb_usuario` FOREIGN KEY (`usuario_cliente_id`) REFERENCES `tb_usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `tb_item_pedido` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantidade` int NOT NULL,
  `preco_unitario` decimal(10,2) NOT NULL,
  `preco_total` decimal(10,2) NOT NULL,
  `observacao` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `pedido_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tb_pedido` (`pedido_id`),
  CONSTRAINT `fk_tb_pedido` FOREIGN KEY (`pedido_id`) REFERENCES `tb_pedido` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;















