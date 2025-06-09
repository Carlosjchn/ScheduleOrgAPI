-- Eliminar datos existentes para evitar conflictos
DELETE FROM eventos;
DELETE FROM horarios;
DELETE FROM horarios_comun;
DELETE FROM usuario_equipo;
DELETE FROM usuarios;
DELETE FROM equipos;

-- Insertar datos en equipos
INSERT INTO equipos (id_equipo, nombre, tipo, hora_inicio_act, hora_fin_act) VALUES
(1, 'Equipo Desarrollo', 'DESARROLLO', '09:00:00', '18:00:00'),
(2, 'Equipo Marketing', 'MARKETING', '08:30:00', '17:30:00'),
(3, 'Equipo Ventas', 'VENTAS', '08:00:00', '17:00:00'),
(4, 'Equipo Soporte', 'SOPORTE', '07:00:00', '19:00:00'),
(5, 'Equipo Recursos Humanos', 'RRHH', '08:00:00', '16:00:00'),
(6, 'Equipo Finanzas', 'FINANZAS', '08:30:00', '17:00:00'),
(7, 'Equipo Diseño', 'DISEÑO', '10:00:00', '19:00:00'),
(8, 'Equipo Operaciones', 'OPERACIONES', '07:30:00', '16:30:00');

-- Insertar datos en usuarios (con contraseñas encriptadas)
INSERT INTO usuarios (id_usuario, nombre, email, contrasena, tipo) VALUES
(2, 'Juan Pérez', 'juan@example.com', '$2a$10$hxo47ecdxj9Levtg1BCOiOSVlkBfdeG3C19tyJumYRHtixyuaVzBi', 'USUARIO'),
(3, 'María López', 'maria@example.com', '$2a$10$hxo47ecdxj9Levtg1BCOiOSVlkBfdeG3C19tyJumYRHtixyuaVzBi', 'USUARIO'),
(4, 'Carlos Rodríguez', 'carlos@example.com', '$2a$10$hxo47ecdxj9Levtg1BCOiOSVlkBfdeG3C19tyJumYRHtixyuaVzBi', 'USUARIO'),
(5, 'Ana Martínez', 'ana@example.com', '$2a$10$hxo47ecdxj9Levtg1BCOiOSVlkBfdeG3C19tyJumYRHtixyuaVzBi', 'USUARIO'),
(6, 'Pedro Sánchez', 'pedro@example.com', '$2a$10$hxo47ecdxj9Levtg1BCOiOSVlkBfdeG3C19tyJumYRHtixyuaVzBi', 'USUARIO'),
(7, 'Laura Gómez', 'laura@example.com', '$2a$10$hxo47ecdxj9Levtg1BCOiOSVlkBfdeG3C19tyJumYRHtixyuaVzBi', 'USUARIO'),
(8, 'Miguel Fernández', 'miguel@example.com', '$2a$10$hxo47ecdxj9Levtg1BCOiOSVlkBfdeG3C19tyJumYRHtixyuaVzBi', 'USUARIO'),
(9, 'Sofía Díaz', 'sofia@example.com', '$2a$10$hxo47ecdxj9Levtg1BCOiOSVlkBfdeG3C19tyJumYRHtixyuaVzBi', 'USUARIO'),
(10, 'Javier Ruiz', 'javier@example.com', '$2a$10$hxo47ecdxj9Levtg1BCOiOSVlkBfdeG3C19tyJumYRHtixyuaVzBi', 'USUARIO'),
(11, 'Carmen Ortiz', 'carmen@example.com', '$2a$10$hxo47ecdxj9Levtg1BCOiOSVlkBfdeG3C19tyJumYRHtixyuaVzBi', 'USUARIO'),
(12, 'David Moreno', 'david@example.com', '$2a$10$hxo47ecdxj9Levtg1BCOiOSVlkBfdeG3C19tyJumYRHtixyuaVzBi', 'USUARIO'),
(13, 'Elena Castro', 'elena@example.com', '$2a$10$hxo47ecdxj9Levtg1BCOiOSVlkBfdeG3C19tyJumYRHtixyuaVzBi', 'USUARIO'),
(14, 'Roberto Jiménez', 'roberto@example.com', '$2a$10$hxo47ecdxj9Levtg1BCOiOSVlkBfdeG3C19tyJumYRHtixyuaVzBi', 'USUARIO'),
(15, 'Lucía Hernández', 'lucia@example.com', '$2a$10$hxo47ecdxj9Levtg1BCOiOSVlkBfdeG3C19tyJumYRHtixyuaVzBi', 'USUARIO'),
(16, 'Antonio Álvarez', 'antonio@example.com', '$2a$10$hxo47ecdxj9Levtg1BCOiOSVlkBfdeG3C19tyJumYRHtixyuaVzBi', 'USUARIO'),
(17, 'Isabel Muñoz', 'isabel@example.com', '$2a$10$hxo47ecdxj9Levtg1BCOiOSVlkBfdeG3C19tyJumYRHtixyuaVzBi', 'USUARIO'),
(18, 'Francisco Torres', 'francisco@example.com', '$2a$10$hxo47ecdxj9Levtg1BCOiOSVlkBfdeG3C19tyJumYRHtixyuaVzBi', 'USUARIO'),
(19, 'Raquel Navarro', 'raquel@example.com', '$2a$10$hxo47ecdxj9Levtg1BCOiOSVlkBfdeG3C19tyJumYRHtixyuaVzBi', 'USUARIO'),
(20, 'Alejandro Romero', 'alejandro@example.com', '$2a$10$hxo47ecdxj9Levtg1BCOiOSVlkBfdeG3C19tyJumYRHtixyuaVzBi', 'USUARIO'),
(21, 'Cristina Serrano', 'cristina@example.com', '$2a$10$hxo47ecdxj9Levtg1BCOiOSVlkBfdeG3C19tyJumYRHtixyuaVzBi', 'ADMIN');

-- Insertar relaciones usuario-equipo con roles
INSERT INTO usuario_equipo (id_usuario, id_equipo, rol) VALUES
-- Equipo 1: Desarrollo
(1, 1, 'MIEMBRO'),
(2, 1, 'MIEMBRO'),
(17, 1, 'JEFE'),
-- Equipo 2: Marketing
(3, 2, 'MIEMBRO'),
(4, 2, 'JEFE'),
(18, 2, 'MIEMBRO'),
-- Equipo 3: Ventas
(5, 3, 'MIEMBRO'),
(6, 3, 'JEFE'),
(19, 3, 'MIEMBRO'),
-- Equipo 4: Soporte
(7, 4, 'MIEMBRO'),
(8, 4, 'JEFE'),
(20, 4, 'MIEMBRO'),
-- Equipo 5: RRHH
(9, 5, 'JEFE'),
(10, 5, 'MIEMBRO'),
-- Equipo 6: Finanzas
(11, 6, 'JEFE'),
(12, 6, 'MIEMBRO'),
-- Equipo 7: Diseño
(13, 7, 'JEFE'),
(14, 7, 'MIEMBRO'),
-- Equipo 8: Operaciones
(15, 8, 'JEFE'),
(16, 8, 'MIEMBRO');

-- Insertar datos en horariosComun
INSERT INTO horarios_comun (id_horario_comun, titulo, id_equipo) VALUES
(1, 'Reunión Semanal Desarrollo', 1),
(2, 'Reunión Mensual Marketing', 2),
(3, 'Reunión Diaria Ventas', 3),
(4, 'Soporte 24/7 Rotativo', 4),
(5, 'Entrevistas Semanales', 5),
(6, 'Cierre Contable Mensual', 6),
(7, 'Revisión de Diseños', 7),
(8, 'Planificación Operativa', 8),
(9, 'Sprint Planning', 1),
(10, 'Sprint Review', 1),
(11, 'Retrospectiva', 1),
(12, 'Análisis de Campañas', 2),
(13, 'Seguimiento de KPIs', 2),
(14, 'Capacitación Ventas', 3),
(15, 'Mantenimiento Preventivo', 4);

-- Insertar datos en horarios
INSERT INTO horarios (id_horario, titulo, id_usuario, id_horario_comun) VALUES
(1, 'Reunión de Planificación', 1, 1),
(2, 'Revisión de Código', 2, 1),
(3, 'Planificación de Campaña', 3, 2),
(4, 'Análisis de Métricas', 4, 2),
(5, 'Seguimiento de Clientes', 5, 3),
(6, 'Contacto con Prospectos', 6, 3),
(7, 'Soporte Técnico Mañana', 7, 4),
(8, 'Soporte Técnico Tarde', 8, 4),
(9, 'Entrevista Candidatos Jr', 9, 5),
(10, 'Entrevista Candidatos Sr', 10, 5),
(11, 'Revisión de Gastos', 11, 6),
(12, 'Preparación de Informes', 12, 6),
(13, 'Diseño de Interfaces', 13, 7),
(14, 'Revisión de Usabilidad', 14, 7),
(15, 'Supervisión de Procesos', 15, 8),
(16, 'Control de Calidad', 16, 8),
(17, 'Sprint Planning Dev', 1, 9),
(18, 'Sprint Planning QA', 2, 9),
(19, 'Sprint Review Frontend', 1, 10),
(20, 'Sprint Review Backend', 2, 10),
(21, 'Retrospectiva Equipo', 1, 11),
(22, 'Análisis Campaña Q1', 3, 12),
(23, 'Análisis Campaña Q2', 4, 12),
(24, 'KPIs Redes Sociales', 3, 13),
(25, 'KPIs Email Marketing', 4, 13),
(26, 'Capacitación Producto Nuevo', 5, 14),
(27, 'Capacitación Técnicas Venta', 6, 14),
(28, 'Mantenimiento Servidores', 7, 15),
(29, 'Mantenimiento Red', 8, 15),
(30, 'Desarrollo Personal', 1, NULL),
(31, 'Formación Técnica', 2, NULL),
(32, 'Investigación de Mercado', 3, NULL),
(33, 'Análisis de Competencia', 4, NULL),
(34, 'Seguimiento Post-Venta', 5, NULL),
(35, 'Documentación Técnica', 7, NULL);

-- Insertar datos en eventos
INSERT INTO eventos (id_evento, titulo, id_usuario, fecha, hora_inicio, hora_fin, id_horario) VALUES
-- Semana 1 (Mayo 27-31, 2025)
(1, 'Reunión de Planificación Semanal', 1, '2025-05-27', '10:00:00', '11:00:00', 1),
(2, 'Revisión de Código Sprint 23', 2, '2025-05-27', '10:00:00', '11:00:00', 2),
(3, 'Planificación Campaña Q3', 3, '2025-05-27', '09:00:00', '10:30:00', 3),
(4, 'Análisis Métricas Mayo', 4, '2025-05-27', '09:00:00', '10:30:00', 4),
(5, 'Seguimiento Clientes VIP', 5, '2025-05-27', '08:30:00', '09:00:00', 5),
(6, 'Contacto Nuevos Prospectos', 6, '2025-05-27', '09:15:00', '10:00:00', 6),
(7, 'Soporte Primera Línea', 7, '2025-05-27', '07:00:00', '13:00:00', 7),
(8, 'Soporte Segunda Línea', 8, '2025-05-27', '13:00:00', '19:00:00', 8),
(9, 'Curso Avanzado React', 1, '2025-05-28', '14:00:00', '15:00:00', 30),
(10, 'Workshop Docker', 2, '2025-05-28', '15:00:00', '16:30:00', 31),
(11, 'Curso Avanzado Angular', 3, '2025-05-28', '10:00:00', '12:00:00', 32),
(12, 'Workshop Kubernetes', 4, '2025-05-28', '14:00:00', '16:00:00', 33),
(13, 'Curso Avanzado Vue.js', 5, '2025-05-28', '08:30:00', '09:00:00', 5),
(14, 'Workshop DevOps', 6, '2025-05-28', '09:15:00', '10:00:00', 6),
(15, 'Soporte Técnico Mañana', 7, '2025-05-28', '07:00:00', '13:00:00', 7),
(16, 'Soporte Técnico Tarde', 8, '2025-05-28', '13:00:00', '19:00:00', 8),
(17, 'Entrevista Candidatos Jr', 9, '2025-05-29', '09:00:00', '11:00:00', 9),
(18, 'Entrevista Candidatos Sr', 10, '2025-05-29', '14:00:00', '16:00:00', 10),
(19, 'Revisión de Gastos', 11, '2025-05-29', '10:00:00', '12:00:00', 11),
(20, 'Preparación de Informes', 12, '2025-05-29', '14:00:00', '17:00:00', 12),

-- Semana 2 (Junio 2-6, 2025)
(21, 'Reunión de Planificación', 1, '2025-06-02', '10:00:00', '11:00:00', 17),
(22, 'Revisión de Código', 2, '2025-06-02', '10:00:00', '11:00:00', 18),
(23, 'Planificación de Campaña', 3, '2025-06-03', '15:00:00', '16:00:00', 19),
(24, 'Análisis de Métricas', 4, '2025-06-03', '15:00:00', '16:00:00', 20),
(25, 'Seguimiento de Clientes', 5, '2025-06-04', '10:00:00', '11:00:00', 1),
(26, 'Contacto con Prospectos', 6, '2025-06-04', '10:00:00', '11:00:00', 2),
(27, 'Soporte Técnico Mañana', 7, '2025-06-04', '09:00:00', '10:30:00', 3),
(28, 'Soporte Técnico Tarde', 8, '2025-06-04', '09:00:00', '10:30:00', 4),
(29, 'Revisión de Gastos', 9, '2025-06-04', '08:30:00', '09:00:00', 5),
(30, 'Preparación de Informes', 10, '2025-06-04', '09:15:00', '10:00:00', 6),
(31, 'Diseño de Interfaces', 1, '2025-06-05', '14:00:00', '15:00:00', 30),
(32, 'Revisión de Usabilidad', 2, '2025-06-05', '15:00:00', '16:30:00', 31),
(33, 'Supervisión de Procesos', 3, '2025-06-05', '10:00:00', '12:00:00', 32),
(34, 'Control de Calidad', 4, '2025-06-05', '14:00:00', '16:00:00', 33),
(35, 'Desarrollo Personal', 5, '2025-06-05', '08:30:00', '09:00:00', 5),
(36, 'Formación Técnica', 6, '2025-06-05', '09:15:00', '10:00:00', 6),
(37, 'Análisis Campaña Q1', 1, '2025-06-06', '16:00:00', '17:00:00', 21),
(38, 'Seguimiento de KPIs', 3, '2025-06-06', '11:00:00', '13:00:00', 22),
(39, 'Análisis de Competencia', 4, '2025-06-06', '14:00:00', '16:00:00', 23),
(40, 'Seguimiento Post-Venta', 5, '2025-06-06', '08:30:00', '09:00:00', 5),

-- Semana 3 (Junio 9-13, 2025)
(41, 'Revisión de Gastos', 3, '2025-06-09', '09:00:00', '11:00:00', 24),
(42, 'Preparación de Informes', 4, '2025-06-09', '14:00:00', '16:00:00', 25),
(43, 'Revisión de Gastos', 5, '2025-06-09', '10:00:00', '12:00:00', 26),
(44, 'Preparación de Informes', 6, '2025-06-09', '14:00:00', '16:00:00', 27),
(45, 'Revisión de Usabilidad', 7, '2025-06-09', '07:00:00', '13:00:00', 7),
(46, 'Control de Calidad', 8, '2025-06-09', '13:00:00', '19:00:00', 8),
(47, 'Revisión de Gastos', 1, '2025-06-10', '09:00:00', '12:00:00', 28),
(48, 'Preparación de Informes', 2, '2025-06-10', '14:00:00', '17:00:00', 29),
(49, 'Revisión de Gastos', 3, '2025-06-11', '10:00:00', '11:00:00', 1),
(50, 'Preparación de Informes', 4, '2025-06-11', '10:00:00', '11:00:00', 2),
(51, 'Revisión de Gastos', 5, '2025-06-11', '09:00:00', '10:30:00', 3),
(52, 'Preparación de Informes', 6, '2025-06-11', '09:00:00', '10:30:00', 4),
(53, 'Revisión de Gastos', 7, '2025-06-11', '08:30:00', '09:00:00', 5),
(54, 'Preparación de Informes', 8, '2025-06-11', '09:15:00', '10:00:00', 6),
(55, 'Revisión de Usabilidad', 1, '2025-06-11', '07:00:00', '13:00:00', 7),
(56, 'Control de Calidad', 2, '2025-06-11', '13:00:00', '19:00:00', 8),
(57, 'Revisión de Gastos', 3, '2025-06-12', '14:00:00', '15:00:00', 30),
(58, 'Preparación de Informes', 4, '2025-06-12', '15:00:00', '16:30:00', 31),
(59, 'Revisión de Gastos', 5, '2025-06-12', '10:00:00', '12:00:00', 32),
(60, 'Preparación de Informes', 6, '2025-06-12', '14:00:00', '16:00:00', 33),

-- Semana 4 (Junio 16-20, 2025)
(61, 'Revisión de Gastos', 1, '2025-06-16', '10:00:00', '11:00:00', 17),
(62, 'Preparación de Informes', 2, '2025-06-16', '10:00:00', '11:00:00', 18),
(63, 'Revisión de Gastos', 3, '2025-06-17', '15:00:00', '16:00:00', 19),
(64, 'Preparación de Informes', 4, '2025-06-17', '15:00:00', '16:00:00', 20),
(65, 'Revisión de Gastos', 5, '2025-06-18', '10:00:00', '11:00:00', 1),
(66, 'Preparación de Informes', 6, '2025-06-18', '10:00:00', '11:00:00', 2),
(67, 'Revisión de Gastos', 7, '2025-06-18', '09:00:00', '10:30:00', 3),
(68, 'Preparación de Informes', 8, '2025-06-18', '09:00:00', '10:30:00', 4),
(69, 'Revisión de Gastos', 9, '2025-06-18', '08:30:00', '09:00:00', 5),
(70, 'Preparación de Informes', 10, '2025-06-18', '09:15:00', '10:00:00', 6),
(71, 'Revisión de Gastos', 1, '2025-06-19', '14:00:00', '15:00:00', 30),
(72, 'Preparación de Informes', 2, '2025-06-19', '15:00:00', '16:30:00', 31),
(73, 'Revisión de Gastos', 3, '2025-06-19', '10:00:00', '12:00:00', 32),
(74, 'Preparación de Informes', 4, '2025-06-19', '14:00:00', '16:00:00', 33),
(75, 'Revisión de Gastos', 5, '2025-06-19', '08:30:00', '09:00:00', 5),
(76, 'Preparación de Informes', 6, '2025-06-19', '09:15:00', '10:00:00', 6),
(77, 'Revisión de Gastos', 1, '2025-06-20', '16:00:00', '17:00:00', 21),
(78, 'Preparación de Informes', 3, '2025-06-20', '11:00:00', '13:00:00', 22),
(79, 'Revisión de Gastos', 4, '2025-06-20', '14:00:00', '16:00:00', 23),
(80, 'Preparación de Informes', 5, '2025-06-20', '08:30:00', '09:00:00', 5),

-- Semana 5 (Junio 23-27, 2025)
(81, 'Revisión de Gastos', 1, '2025-06-23', '10:00:00', '11:00:00', 1),
(82, 'Preparación de Informes', 2, '2025-06-23', '10:00:00', '11:00:00', 2),
(83, 'Revisión de Gastos', 3, '2025-06-23', '09:00:00', '10:30:00', 3),
(84, 'Preparación de Informes', 4, '2025-06-23', '09:00:00', '10:30:00', 4),
(85, 'Revisión de Gastos', 5, '2025-06-23', '08:30:00', '09:00:00', 5),
(86, 'Preparación de Informes', 6, '2025-06-23', '09:15:00', '10:00:00', 6),
(87, 'Revisión de Gastos', 7, '2025-06-23', '07:00:00', '13:00:00', 7),
(88, 'Preparación de Informes', 8, '2025-06-23', '13:00:00', '19:00:00', 8),
(89, 'Revisión de Gastos', 9, '2025-06-24', '09:00:00', '11:00:00', 9),
(90, 'Preparación de Informes', 10, '2025-06-24', '14:00:00', '16:00:00', 10),
(91, 'Revisión de Gastos', 11, '2025-06-24', '10:00:00', '12:00:00', 11),
(92, 'Preparación de Informes', 12, '2025-06-24', '14:00:00', '17:00:00', 12),
(93, 'Revisión de Gastos', 13, '2025-06-25', '10:00:00', '12:00:00', 13),
(94, 'Preparación de Informes', 14, '2025-06-25', '14:00:00', '16:00:00', 14),
(95, 'Revisión de Gastos', 15, '2025-06-25', '08:00:00', '10:00:00', 15),
(96, 'Preparación de Informes', 16, '2025-06-25', '10:00:00', '12:00:00', 16),
(97, 'Revisión de Gastos', 17, '2025-06-25', '14:00:00', '15:00:00', 30),
(98, 'Preparación de Informes', 18, '2025-06-25', '15:00:00', '16:30:00', 32),
(99, 'Revisión de Gastos', 19, '2025-06-25', '10:00:00', '12:00:00', 34),
(100, 'Preparación de Informes', 20, '2025-06-25', '14:00:00', '16:00:00', 35);