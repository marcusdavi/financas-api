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
values ("Pet Bath", '2020-09-06', '2020-09-06', 35.00, "Shower and hygienic grooming", "DEBIT", 5, 1);

INSERT INTO entry (description, expiration_date, pay_date, value, note, type, id_category, id_person)
values ("Globo Drugstore", '2020-09-18', '2020-09-18', 300.52, "Flu remedies", "DEBIT", 4, 1);

INSERT INTO entry (description, expiration_date, pay_date, value, note, type, id_category, id_person)
values ("Briefcase Sale", '2020-09-19', '2020-09-19', 40.00, "OLX Sale", "CREDIT", 6, 1);

INSERT INTO entry (description, expiration_date, pay_date, value, note, type, id_category, id_person)
values ("Pet Bath", '2020-09-20', '2020-09-20', 30.00, "only Shower", "DEBIT", 5, 1);

INSERT INTO entry (description, expiration_date, pay_date, value, note, type, id_category, id_person)
values ("Energy bill", '2020-09-20', '2020-09-06', 89.15, "09-2020 bill", "DEBIT", 3, 1);

INSERT INTO entry (description, expiration_date, pay_date, value, note, type, id_category, id_person)
values ("Salary", '2020-09-01', '2020-09-01', 1045.15, "08-2020 salary", "CREDIT", 3, 1);

INSERT INTO entry (description, expiration_date, pay_date, value, note, type, id_category, id_person)
values ("Outback", '2020-09-14', '2020-09-14', 1045.15, "datting aniversary", "CREDIT", 2, 1);

INSERT INTO entry (description, expiration_date, pay_date, value, note, type, id_category, id_person)
values ("Cinema", '2020-09-14', '2020-09-14', 32.00, "Tickests for Avengers 9 and popcorn", "CREDIT", 1, 1);

INSERT INTO entry (description, expiration_date, pay_date, value, note, type, id_category, id_person)
values ("Pet Bath", '2020-08-06', '2020-08-06', 35.99, "Shower and hygienic grooming", "DEBIT", 5, 2);

INSERT INTO entry (description, expiration_date, pay_date, value, note, type, id_category, id_person)
values ("Globo Drugstore", '2020-08-18', '2020-08-18', 300.52, "Flu remedies", "DEBIT", 4, 2);

INSERT INTO entry (description, expiration_date, pay_date, value, note, type, id_category, id_person)
values ("Briefcase Sale", '2020-08-19', '2020-08-19', 40.99, "OLX Sale", "CREDIT", 6, 2);

INSERT INTO entry (description, expiration_date, pay_date, value, note, type, id_category, id_person)
values ("Pet Bath", '2020-08-20', '2020-08-20', 30.99, "only Shower", "DEBIT", 5, 2);

INSERT INTO entry (description, expiration_date, pay_date, value, note, type, id_category, id_person)
values ("Energy bill", '2020-08-20', '2020-08-06', 89.15, "09-2020 bill", "DEBIT", 3, 2);

INSERT INTO entry (description, expiration_date, pay_date, value, note, type, id_category, id_person)
values ("Salary", '2020-08-01', '2020-08-01', 1045.15, "08-2020 salary", "CREDIT", 3, 2);

INSERT INTO entry (description, expiration_date, pay_date, value, note, type, id_category, id_person)
values ("Outback", '2020-08-14', '2020-08-14', 1045.15, "datting aniversary", "CREDIT", 2, 2);

INSERT INTO entry (description, expiration_date, pay_date, value, note, type, id_category, id_person)
values ("Cinema", '2020-08-14', '2020-08-14', 32.99, "Tickests for Avengers 9 and popcorn", "CREDIT", 1, 2);