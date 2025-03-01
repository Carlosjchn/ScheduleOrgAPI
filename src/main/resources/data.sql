CREATE DATABASE IF NOT EXISTS horariosapp;
-- First, let's clear existing data
DELETE FROM horarios;
DELETE FROM usuarios;
DELETE FROM equipos;

-- Equipos (Teams) remain the same
INSERT INTO equipos (nombre, tipo, hora_inicio_act, hora_fin_act) VALUES
('Equipo Frontend', 'Desarrollo', '09:00:00', '18:00:00'),
('Equipo Backend', 'Desarrollo', '08:00:00', '17:00:00'),
('Equipo Mobile', 'Desarrollo', '10:00:00', '19:00:00'),
('Equipo QA', 'Testing', '08:30:00', '17:30:00'),
('Equipo DevOps', 'Operaciones', '07:00:00', '16:00:00'),
('Equipo UX/UI', 'Diseño', '09:30:00', '18:30:00'),
('Equipo Seguridad', 'Operaciones', '08:00:00', '17:00:00'),
('Equipo Data Science', 'Desarrollo', '10:00:00', '19:00:00');

-- Users with correct BCrypt hash for "password123"
INSERT INTO usuarios (nombre, email, contrasena, tipo, id_equipo) VALUES
('Admin System', 'admin.system@company.com', '$2a$10$GlKv.5ZX3dEJZ.SZfbk.YOmi.KsPhzMqZgST26uqGIhFj3drmZOJi', 'Admin', 1),
('Admin Security', 'admin.security@company.com', '$2a$10$GlKv.5ZX3dEJZ.SZfbk.YOmi.KsPhzMqZgST26uqGIhFj3drmZOJi', 'Admin', 7),
('Sarah Lead', 'sarah.lead@company.com', '$2a$10$GlKv.5ZX3dEJZ.SZfbk.YOmi.KsPhzMqZgST26uqGIhFj3drmZOJi', 'Jefe', 1),
('Mike Director', 'mike.director@company.com', '$2a$10$GlKv.5ZX3dEJZ.SZfbk.YOmi.KsPhzMqZgST26uqGIhFj3drmZOJi', 'Jefe', 2),
('Lisa Manager', 'lisa.manager@company.com', '$2a$10$GlKv.5ZX3dEJZ.SZfbk.YOmi.KsPhzMqZgST26uqGIhFj3drmZOJi', 'Jefe', 3),
('John Boss', 'john.boss@company.com', '$2a$10$GlKv.5ZX3dEJZ.SZfbk.YOmi.KsPhzMqZgST26uqGIhFj3drmZOJi', 'Jefe', 4),
('Alex Frontend', 'alex.frontend@company.com', '$2a$10$GlKv.5ZX3dEJZ.SZfbk.YOmi.KsPhzMqZgST26uqGIhFj3drmZOJi', 'Trabajador', 1),
('Emma Backend', 'emma.backend@company.com', '$2a$10$GlKv.5ZX3dEJZ.SZfbk.YOmi.KsPhzMqZgST26uqGIhFj3drmZOJi', 'Trabajador', 2),
('Carlos Mobile', 'carlos.mobile@company.com', '$2a$10$GlKv.5ZX3dEJZ.SZfbk.YOmi.KsPhzMqZgST26uqGIhFj3drmZOJi', 'Trabajador', 3),
('Diana QA', 'diana.qa@company.com', '$2a$10$GlKv.5ZX3dEJZ.SZfbk.YOmi.KsPhzMqZgST26uqGIhFj3drmZOJi', 'Trabajador', 4),
('Frank DevOps', 'frank.devops@company.com', '$2a$10$GlKv.5ZX3dEJZ.SZfbk.YOmi.KsPhzMqZgST26uqGIhFj3drmZOJi', 'Trabajador', 5),
('Grace Designer', 'grace.designer@company.com', '$2a$10$GlKv.5ZX3dEJZ.SZfbk.YOmi.KsPhzMqZgST26uqGIhFj3drmZOJi', 'Trabajador', 6),
('Henry Security', 'henry.security@company.com', '$2a$10$GlKv.5ZX3dEJZ.SZfbk.YOmi.KsPhzMqZgST26uqGIhFj3drmZOJi', 'Trabajador', 7),
('Isabel Data', 'isabel.data@company.com', '$2a$10$GlKv.5ZX3dEJZ.SZfbk.YOmi.KsPhzMqZgST26uqGIhFj3drmZOJi', 'Trabajador', 8);

-- Previous Week (Feb 10-14, 2025)
INSERT INTO horarios (fecha, hora_inicio, hora_fin, id_usuario) VALUES
('2025-02-10', '09:00:00', '18:00:00', 3),
('2025-02-10', '08:00:00', '17:00:00', 4),
('2025-02-10', '10:00:00', '19:00:00', 5),
('2025-02-11', '09:00:00', '18:00:00', 6),
('2025-02-11', '08:00:00', '17:00:00', 7),
('2025-02-12', '10:00:00', '19:00:00', 8),
('2025-02-13', '08:30:00', '17:30:00', 9),
('2025-02-14', '09:00:00', '18:00:00', 10);

-- Current Week (Feb 17-21, 2025)
INSERT INTO horarios (fecha, hora_inicio, hora_fin, id_usuario) VALUES
('2025-02-17', '09:00:00', '18:00:00', 3),
('2025-02-17', '08:00:00', '17:00:00', 4),
('2025-02-18', '10:00:00', '19:00:00', 5),
('2025-02-19', '08:30:00', '17:30:00', 6),
('2025-02-20', '09:00:00', '18:00:00', 7),
('2025-02-20', '08:00:00', '17:00:00', 8),
('2025-02-20', '10:00:00', '19:00:00', 9),
('2025-02-21', '08:30:00', '17:30:00', 10);

-- Next Week (Feb 24-28, 2025)
INSERT INTO horarios (fecha, hora_inicio, hora_fin, id_usuario) VALUES
('2025-02-24', '09:00:00', '18:00:00', 3),
('2025-02-24', '08:00:00', '17:00:00', 4),
('2025-02-25', '10:00:00', '19:00:00', 5),
('2025-02-26', '08:30:00', '17:30:00', 6),
('2025-02-27', '09:00:00', '18:00:00', 7),
('2025-02-28', '08:00:00', '17:00:00', 8);