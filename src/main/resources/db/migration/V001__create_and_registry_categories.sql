CREATE TABLE category (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO category (name) values ('Lazer');
INSERT INTO category (name) values ('Alimentação');
INSERT INTO category (name) values ('Moradia');
INSERT INTO category (name) values ('Farmácia');
INSERT INTO category (name) values ('Pet');
INSERT INTO category (name) values ('Outros');