ALTER TABLE tb_pedido  add codigo varchar(36) not null after id;
UPDATE  tb_pedido set codigo=UUID();
ALTER table tb_pedido add constraint uk_pedido_codigo unique (codigo);
