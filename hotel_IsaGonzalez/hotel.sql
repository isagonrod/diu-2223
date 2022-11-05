CREATE DATABASE hotel;

USE hotel;

CREATE TABLE cliente (
     dni VARCHAR(9) NOT NULL,
     nombre VARCHAR(50),
     apellidos VARCHAR(50),
     direccion VARCHAR(50),
     localidad VARCHAR(50),
     provincia VARCHAR(20),
     PRIMARY KEY(dni)
) ENGINE=InnoDB;

CREATE TABLE reserva (
    codReserva INT NOT NULL AUTO_INCREMENT,
    fechaLlegada DATE,
    fechaSalida DATE,
    numHabitaciones INT,
    tipoHabitacion VARCHAR(50),
    fumador BIT, /* TRUE = 1, FALSE = 0 */
    regimenAlojamiento VARCHAR(10),
    dniCliente VARCHAR(9),
    PRIMARY KEY(codReserva),
    FOREIGN KEY(dniCliente) FROM cliente(dni)
) ENGINE=InnoDB;
