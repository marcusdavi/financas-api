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
values ('João da Silva', TRUE, "Av Hermes da Fonseca", "1528", "Ap 902", "Tirol", "59.020-095", "Natal", "RN", "Brazil");

INSERT INTO person (name, active, street, number, complement, neighborhood, postal_code, city, state, country)
values ('Marcus dos Santos', FALSE, "Av Senador Salgado Filho", "1800", "Casa", "Lagoa Nova", "59.076-000", "Natal", "RN", "Brazil");