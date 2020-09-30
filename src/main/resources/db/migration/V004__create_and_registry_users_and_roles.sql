CREATE TABLE user (
	id BIGINT(20) PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permission (
	id BIGINT(20) PRIMARY KEY,
	description VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_permission (
	id_user BIGINT(20) NOT NULL,
	id_permission BIGINT(20) NOT NULL,
	PRIMARY KEY (id_user, id_permission),
	FOREIGN KEY (id_user) REFERENCES user(id),
	FOREIGN KEY (id_permission) REFERENCES permission(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user (id, name, email, password) values (1, 'Administrator', 'admin@financas.com', '$2a$10$0bfx5KLVia7W8vu65olYYemg.mnjtMOhTa9f0VyIDCphb7sAFte3W');
INSERT INTO user (id, name, email, password) values (2, 'Marcus', 'marcus@financas.com', '$2a$10$PxTTqFqrVVXWl4CQSHGyKOFjB60fokH2onucTalZC3jWeeDDbQFVW');

INSERT INTO permission (id, description) values (1, 'permission_CREATE_CATEGORY');
INSERT INTO permission (id, description) values (2, 'permission_SEARCH_CATEGORY');

INSERT INTO permission (id, description) values (3, 'permission_CREATE_PERSON');
INSERT INTO permission (id, description) values (4, 'permission_REMOVE_PERSON');
INSERT INTO permission (id, description) values (5, 'permission_SEARCH_PERSON');

INSERT INTO permission (id, description) values (6, 'permission_CREATE_ENTRY');
INSERT INTO permission (id, description) values (7, 'permission_REMOVE_ENTRY');
INSERT INTO permission (id, description) values (8, 'permission_SEARCH_ENTRY');

-- admin
INSERT INTO user_permission (id_user, id_permission) values (1, 1);
INSERT INTO user_permission (id_user, id_permission) values (1, 2);
INSERT INTO user_permission (id_user, id_permission) values (1, 3);
INSERT INTO user_permission (id_user, id_permission) values (1, 4);
INSERT INTO user_permission (id_user, id_permission) values (1, 5);
INSERT INTO user_permission (id_user, id_permission) values (1, 6);
INSERT INTO user_permission (id_user, id_permission) values (1, 7);
INSERT INTO user_permission (id_user, id_permission) values (1, 8);

-- marcus
INSERT INTO user_permission (id_user, id_permission) values (2, 2);
INSERT INTO user_permission (id_user, id_permission) values (2, 5);
INSERT INTO user_permission (id_user, id_permission) values (2, 8);