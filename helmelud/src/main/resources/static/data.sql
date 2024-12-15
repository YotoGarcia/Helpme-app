-- CREAR USUARIO A BAJO NIVEL
USE helpme_iud;

INSERT INTO usuarios(id, username, password, nombre,
                  apellidos, enabled, fecha_nacimiento, created_at)
VALUES (1, 'jonathan.garciaab@est.iudigital.edu.co', '$2a$10$0NkkqVxPOCO23Qu1gHdMNer4IEw0aaz9XKRKpUkfKz2Ou7.2INbjC',
        'Jonathan',
        'Garcia', true, '1996-02-11', current_timestamp);

-- CREACION ROLES A BAJO NIVEL

INSERT INTO roles (id, nombre, descripcion)
VALUES (1, "ROLE_ADMIN", "Administrador del sistema");

INSERT INTO roles (id, nombre, descripcion)
VALUES (2, "ROLE_USER", "Usuario normal auto-registrado");

-- ASOCIACION DE ROLES A BAJO NIVEL AL ADMIN

INSERT INTO roles_usuarios(usuarios_id, roles_id)
VALUES (1, 1);

INSERT INTO roles_usuarios(usuarios_id, roles_id)
VALUES (1, 2);
