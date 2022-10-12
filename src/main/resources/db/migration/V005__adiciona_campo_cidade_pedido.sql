ALTER TABLE sgc.tb_pedido ADD cidade_id BIGINT NOT NULL;
ALTER TABLE sgc.tb_pedido ADD CONSTRAINT fk_tb_pedido_cidade_id FOREIGN KEY (cidade_id) REFERENCES sgc.tb_cidade(id);
