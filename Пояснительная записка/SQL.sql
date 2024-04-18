CREATE DATABASE  if not EXISTS medicineDeliverySimulator;

USE medicineDeliverySimulator;

CREATE TABLE `type_medic` (
	`type` VARCHAR(40) NOT NULL,
	PRIMARY KEY (`type`)
);
CREATE TABLE `medic` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(100) NOT NULL,
	`size_type` VARCHAR(10),
	`type_medic` VARCHAR(40),
	PRIMARY KEY (`id`),
	FOREIGN KEY (type_medic)  REFERENCES type_medic (type)
);
CREATE TABLE `workhouse` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`medic` INT NOT NULL,
	`count` INT NOT NULL,
	`date_getting` DATE NOT NULL,
	`date_dead` DATE NOT NULL,
	`price_getting` FLOAT,
	`price_selling` FLOAT,
	PRIMARY KEY (`id`),
	FOREIGN KEY (medic)  REFERENCES medic (id)
);
CREATE TABLE `role` (
	`role` VARCHAR(30) NOT NULL,
	PRIMARY KEY (`role`)
);
INSERT INTO role (role) VALUES ("user"),("admin");
CREATE TABLE `users` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`fio` VARCHAR(200) NOT NULL,
	`adres` VARCHAR(200) NOT NULL,
	`phone` TEXT(15) NOT NULL,
	`role` VARCHAR(30) NOT NULL,
	PRIMARY KEY (`id`),
	FOREIGN KEY (role)  REFERENCES role (role)
	
);
CREATE TABLE `order` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`users` INT NOT NULL,
	`medic` INT NOT NULL,
	`count` INT NOT NULL,
	`date_getting` DATE NOT NULL,
	`is_got` BOOLEAN,
	PRIMARY KEY (`id`),
	FOREIGN KEY (users)  REFERENCES users (id),
	FOREIGN KEY (medic)  REFERENCES medic (id)
);
