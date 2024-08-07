create table usuario(
                        id_usuario serial not null primary key,
                        nome varchar(50),
                        cpf varchar(11),
                        email varchar(50),
                        permissao varchar(40),
                        login varchar(25),
                        senha varchar(100)
);

create table unidade_negocio(
                                id_unidade serial not null primary key,
                                nome varchar(50),
                                cnpj varchar(14)
);

create table cliente(
                        id_usuario int primary key,
                        cnpj_unidade varchar(14),
                        id_unidade int,

                        FOREIGN KEY (id_unidade) REFERENCES unidade_negocio(id_unidade),
                        FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

create table desenvolvedor(
                              id_usuario serial not null primary key,
                              data_nasc DATE,
                              salario NUMERIC(19,2),
                              cargo varchar(50),

                              FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

create table produto(
                        id_produto serial not null primary key,
                        nome_produto varchar(50),
                        id_unidade int,

                        FOREIGN KEY (id_unidade) REFERENCES unidade_negocio(id_unidade)
);

create table chamado(
                        id_chamado serial not null primary key,
                        titulo varchar(50),
                        descricao TEXT,
                        status varchar(50),
                        gravidade varchar(50),
                        recurso bytea,
                        id_usuario int,
                        id_produto int,

                        FOREIGN KEY (id_produto) REFERENCES produto(id_produto),
                        FOREIGN KEY (id_usuario) REFERENCES cliente(id_usuario)
);

create table tarefa(
                       id_tarefa serial not null primary key,
                       titulo varchar(50),
                       descricao TEXT,
                       status varchar(50),
                       id_usuario int,

                       FOREIGN KEY (id_usuario) REFERENCES desenvolvedor(id_usuario)
);