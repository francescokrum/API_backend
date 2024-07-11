ALTER TABLE chamado
DROP COLUMN id_cliente;

ALTER TABLE chamado
    ADD COLUMN id_usuario int,
    ADD CONSTRAINT fk_chamado_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);

ALTER TABLE chamado
    DROP COLUMN id_usuario;

ALTER TABLE chamado
    ADD COLUMN id_cliente int,
    ADD CONSTRAINT fk_chamado_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente);