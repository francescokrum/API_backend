-- Inserção de unidades de negócio
INSERT INTO unidade_negocio (nome, cnpj)
VALUES
    ('Unidade A', '12345678000100'),
    ('Unidade B', '98765432000199');

INSERT INTO usuario (nome, cpf, email, permissao, login, senha)
VALUES ('Admin User', '12345678900', 'admin@example.com', 'ADMIN', 'admin', '$2a$10$5vdDvZ81xwZsRjST.9HbN.LIetFq6Iy6W5VUoXpZEDn8Km6dAluUO');

INSERT INTO desenvolvedor (data_nasc, salario, cargo, id_usuario)
VALUES ('1990-01-01', 5000.00, 'Desenvolvedor Senior', 5);

