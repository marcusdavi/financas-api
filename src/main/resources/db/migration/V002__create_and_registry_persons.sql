CREATE TABLE person (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	active BOOLEAN NOT NULL,
	street VARCHAR(50),
	number VARCHAR(50),
	complement VARCHAR(50),
	neighborhood VARCHAR(50),
	postal_code VARCHAR(50),
	city VARCHAR(50),
	state VARCHAR(50),
	country VARCHAR(50) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO person (name, active, street, number, complement, neighborhood, postal_code, city, state, country)
values ('Marcus', TRUE, "Rodrigues Alves", "1071", "Ap 302", "Tirol", "59020255", "Natal", "RN", "Brazil");

INSERT INTO person (name, active, street, number, neighborhood, postal_code, city, state, country)
values ('Susana', FALSE, "Afonso Pena", "1071", "Tirol", "59020166", "Natal", "RN", "Brazil");


INSERT INTO person (name, active, street, number, neighborhood, postal_code, city, state, country)
values ('Thorzinho', FALSE, "Campos Sales", "672", "Tirol", "59020100", "Natal", "RN", "Brazil");
