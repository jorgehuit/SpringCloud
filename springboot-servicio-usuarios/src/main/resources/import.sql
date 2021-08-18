INSERT INTO usuario (username, password, nombre, app, email, enabled) VALUES('Huit', 'Huit', 'Huit', 'GV', 'huit@email', 1);
INSERT INTO usuario (username, password, nombre, app, email, enabled) VALUES('Ale', 'Ale', 'Ale', 'ZR', 'ale@email', 1);
INSERT INTO usuario (username, password, nombre, app, email, enabled) VALUES('Furia', 'Furia', 'Furia', 'garritas', 'furia@email', 1);
INSERT INTO usuario (username, password, nombre, app, email, enabled) VALUES('Mushu', 'Mushu', 'Mushu', 'garritas', 'mushu@email', 1);

INSERT INTO role (nombre) VALUES ('ROLE_USER');
INSERT INTO role (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuario_role (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuario_role (usuario_id, role_id) VALUES (2, 2);
INSERT INTO usuario_role (usuario_id, role_id) VALUES (3, 1);
INSERT INTO usuario_role (usuario_id, role_id) VALUES (4, 2);
INSERT INTO usuario_role (usuario_id, role_id) VALUES (2, 1);
