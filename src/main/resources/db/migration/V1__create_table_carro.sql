CREATE TABLE carro (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome varchar(100) NOT NULL,
    marca varchar(100) NOT NULL,
    grupo varchar(100) NOT NULL,
    CONSTRAINT UK_NOME UNIQUE(nome)
);