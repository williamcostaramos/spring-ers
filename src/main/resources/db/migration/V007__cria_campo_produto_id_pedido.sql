ALTER TABLE sgc.tb_item_pedido ADD produto_id BIGINT NOT NULL;
ALTER TABLE sgc.tb_item_pedido ADD CONSTRAINT fk_item_pedido_produto_id FOREIGN KEY (produto_id) REFERENCES sgc.tb_produto(id);
