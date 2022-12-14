INSERT INTO productos (nombre, codigo, stock, precio, descripcion) VALUES('Balón', 'BA4ENDJ1I@RE', '0', '30', 'Balón de futbol');
INSERT INTO productos (nombre, codigo, stock, precio, descripcion) VALUES('Jenga', 'JA4KFDJ1_FJA', '1', '40', 'Jenga de madera');
INSERT INTO productos (nombre, codigo, stock, precio, descripcion) VALUES('CD de Juan Gabriel', 'CDA4DJ1I.ALT', '1', '15', 'CD original de Juan Gabriel desde Bellas Artes');


INSERT INTO usuarios (numero_De_Cuenta, nombre, numero_De_Celular, correo_Electronico, carrera, contrasena, puma_Puntos,productos_rentados, enabled) VALUES ('100000000', 'Dante Torobolino', '5544332211', 'correo1@unam.mx' , 'Actuaria',  '$2a$10$b/cjHQrLX8JQhSICVlB/X.uZBrXD1fezkehBjin.Jh8KSU.1C0waK', '100',2, true);
INSERT INTO usuarios (numero_De_Cuenta, nombre, numero_De_Celular, correo_Electronico, carrera, contrasena, puma_Puntos,productos_rentados, enabled) VALUES ('200000000', 'Administrador', '5500112233', 'correo2@unam.mx' , 'Biologia', '$2a$10$m9gXzIGyfAXt0BkHAAXyDuqFhaevQnd0dg73qpWjDf.2u9WLyz1p6' , '100' ,3,true);
INSERT INTO usuarios (numero_De_Cuenta, nombre, numero_De_Celular, correo_Electronico, carrera, contrasena, puma_Puntos,productos_rentados, enabled) VALUES ('300000000', 'Provedor', '1234567890', 'correo3@unam.mx' , 'Computacion', '$2a$10$m9gXzIGyfAXt0BkHAAXyDuqFhaevQnd0dg73qpWjDf.2u9WLyz1p6' , '100' ,0,true);

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO roles (nombre) VALUES ('ROLE_PROV');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (3, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (3, 3);
