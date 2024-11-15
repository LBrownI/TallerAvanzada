-- creo base de datos
create database if not exists solemne2;

-- indico que usare la base de datos solumne2
use solemne2;

-- creo tabla donde se guradaran los dtos de las tareas
create table tareas (
	id int auto_increment primary key,
	nombre varchar(50),
	descripcion varchar(500),
	estado boolean default true
);

-- inserto  treas de ejemplo
insert into tareas (nombre, descripcion) VALUES 
('Estudiar POO', 'Revisar conceptos de Programación Orientada a Objetos en Java, incluyendo clases, objetos, herencia y polimorfismo.'),
('Practicar desarrollo web', 'Crear una aplicación web simple utilizando servlets y JSP.'),
('Revisar patrón MVC', 'Estudiar el patrón de diseño Modelo-Vista-Controlador y cómo implementarlo en Java.'),
('Implementar DAO', 'Desarrollar una capa de acceso a datos (DAO) para una aplicación Java.'),
('Resolver ejercicios de Java', 'Completar ejercicios prácticos de Java para reforzar los conceptos aprendidos.');

-- muestro los datos de la tabla tareas
select * from tareas;
