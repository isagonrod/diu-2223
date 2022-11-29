CREATE DATABASE conversor;

USE conversor;

CREATE TABLE IF NOT EXISTS moneda (
    codigo INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50),
    multiplicador FLOAT,
    PRIMARY KEY(codigo)
) ENGINE=innoDB;

INSERT INTO moneda VALUES (1, 'Euro', 1.5);
INSERT INTO moneda VALUES (2, 'Dollar', 2);