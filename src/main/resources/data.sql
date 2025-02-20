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

-- Horarios remain the same
INSERT INTO horarios (fecha, hora_inicio, hora_fin, id_usuario) VALUES
-- ... (rest of the horarios inserts remain unchanged)
-- Horarios (Schedules for the last week)
INSERT INTO horarios (fecha, hora_inicio, hora_fin, id_usuario) VALUES
('2024-02-12', '09:00:00', '18:00:00', 3),
('2024-02-12', '08:00:00', '17:00:00', 4),
('2024-02-12', '10:00:00', '19:00:00', 5),
('2024-02-12', '08:30:00', '17:30:00', 6),
('2024-02-13', '09:00:00', '18:00:00', 7),
('2024-02-13', '08:00:00', '17:00:00', 8),
('2024-02-13', '10:00:00', '19:00:00', 9),
('2024-02-13', '08:30:00', '17:30:00', 10),
('2024-02-14', '09:00:00', '18:00:00', 11),
('2024-02-14', '08:00:00', '17:00:00', 12),
('2024-02-14', '10:00:00', '19:00:00', 13),
('2024-02-14', '08:30:00', '17:30:00', 14),
('2024-02-15', '09:00:00', '18:00:00', 3),
('2024-02-15', '08:00:00', '17:00:00', 4),
('2024-02-15', '10:00:00', '19:00:00', 5),
('2024-02-15', '08:30:00', '17:30:00', 6),
('2024-02-16', '09:00:00', '18:00:00', 7),
('2024-02-16', '08:00:00', '17:00:00', 8),
('2024-02-16', '10:00:00', '19:00:00', 9),
('2024-02-16', '08:30:00', '17:30:00', 10);