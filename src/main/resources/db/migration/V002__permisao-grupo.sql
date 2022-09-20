CREATE TABLE `tb_permissao`
(
    `id`        bigint NOT NULL AUTO_INCREMENT,
    `nome`      varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
    `descricao` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB  CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tb_grupo_permissao`
(
    `id_tb_grupo`     bigint NOT NULL,
    `id_tb_permissao` bigint NOT NULL,
    KEY               `fk_grupo_id` (`id_tb_grupo`),
    KEY               `fk_permissao_id` (`id_tb_permissao`),
    CONSTRAINT `fk_gp_grupo_id` FOREIGN KEY (`id_tb_grupo`) REFERENCES `tb_grupo` (`id`),
    CONSTRAINT `fk_gp_permissao_id` FOREIGN KEY (`id_tb_permissao`) REFERENCES `tb_permissao` (`id`)
) ENGINE=InnoDB  CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;