CREATE DATABASE agenda;

USE agenda;

CREATE TABLE IF NOT EXISTS person (
	idPerson INT NOT NULL AUTO_INCREMENT,
	firstName VARCHAR(50), 
	lastName VARCHAR(50), 
	street VARCHAR(50), 
	city VARCHAR(20),
	postalCode INT(5), 
	birthday DATE, 
	PRIMARY KEY(idPerson)
) ENGINE=InnoDB;
