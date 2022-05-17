CREATE TABLE usuario (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome varchar(120) NOT NULL,
    cpf varchar(11) NOT NULL,
    email varchar(50) NOT NULL,
    CONSTRAINT UK_CPF UNIQUE(cpf),
    CONSTRAINT UK_EMAIL UNIQUE(email)
);