CREATE DATABASE CL3_CORNEJOCANCINO;

USE CL3_CORNEJOCANCINO;

CREATE TABLE producto(
id int primary key auto_increment,
anio varchar(10),
descripcion varchar(120),
nombre varchar(30)
)
select * from producto;

insert into producto values(1,'18/02/2023','pc gamer ultimo modelo','pc');
insert into producto values(2,'13/06/2023','monitor lg','monitor');
insert into producto values(3,'14/05/2023','teclado antryx','teclado');
insert into producto values(4,'12/07/2023','mouse antryx','mouse');
