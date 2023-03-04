CREATE TABLE tb_restaurante_responsavel
(
    restaurante_id BIGINT NOT NULL,
    usuario_id     BIGINT NOT NULL,
    CONSTRAINT fk_restaurante_responsavel_restaurante_id FOREIGN KEY (restaurante_id) REFERENCES sgc.tb_restaurante (id),
    CONSTRAINT fk_restaurante_responsavel_usuario_id FOREIGN KEY (usuario_id) REFERENCES sgc.tb_usuario (id)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;