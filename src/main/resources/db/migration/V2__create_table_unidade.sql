CREATE TABLE unidade (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    placa varchar(7) NOT NULL,
    chassi varchar(17) NOT NULL,
    ano int NOT NULL,
    reservado boolean NOT NULL,
    version int NOT NULL,
    carro_id bigint NOT NULL,
    CONSTRAINT UK_PLACA UNIQUE(placa),
    CONSTRAINT UK_CHASSI UNIQUE(chassi)
);