CREATE TABLE entry (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(50) NOT NULL,
	expiration_date DATE NOT NULL,
	pay_date DATE,
	value DECIMAL(10,2) NOT NULL,
	note VARCHAR(100),
	type VARCHAR(20) NOT NULL,
	id_category BIGINT(20) NOT NULL,
	id_person BIGINT(20) NOT NULL,
	FOREIGN KEY (id_category) REFERENCES category(id),
	FOREIGN KEY (id_person) REFERENCES person(id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO entry (description, expiration_date, pay_date, value, note, type, id_category, id_person)
values ("Banho PetPrime", '2020-09-20', null, 30.00, "Banho de 20 de Setembro", "DEBIT", 5, 1);

INSERT INTO entry (description, expiration_date, pay_date, value, note, type, id_category, id_person)
values ("Pague Menos", '2020-09-18', null, 300.52, "Remédios pra Gripe", "DEBIT", 4, 1);

INSERT INTO entry (description, expiration_date, pay_date, value, note, type, id_category, id_person)
values ("Venda das Maletas", '2020-09-19', null, 40.00, "Venda na OLX", "CREDIT", 6, 1);