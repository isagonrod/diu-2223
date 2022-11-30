CREATE DATABASE conversor;

USE conversor;

CREATE TABLE IF NOT EXISTS monedas (
    codigo INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50),
    multiplicador FLOAT,
    PRIMARY KEY(codigo)
) ENGINE=innoDB;

INSERT INTO monedas VALUES (1, 'Yen', 1);
INSERT INTO monedas VALUES (2, 'Lira', 0.5);
INSERT INTO monedas VALUES (3, 'Dólar Americano', 1.15);
INSERT INTO monedas VALUES (4, 'Libra esterlina', 0.65);