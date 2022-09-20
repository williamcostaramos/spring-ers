ALTER TABLE sgc.tb_produto ADD restaurante_id BIGINT NOT NULL;
ALTER TABLE sgc.tb_produto ADD CONSTRAINT tb_produto_FK FOREIGN KEY (restaurante_id) REFERENCES sgc.tb_restaurante(id);
