CREATE DATABASE agenda;

USE agenda;

CREATE TABLE IF NOT EXISTS person (
	id INT(2) NOT NULL AUTO_INCREMENT,
	firstName VARCHAR(50), 
	lastName VARCHAR(50), 
	street VARCHAR(50), 
	city VARCHAR(50),
	postalCode INT(5), 
	birthday DATE, 
	PRIMARY KEY(id)
) ENGINE=InnoDB;
