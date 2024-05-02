DROP DATABASE IF EXISTS PRUEBA;

CREATE DATABASE PRUEBA;

USE PRUEBA;

DROP TABLA IF EXISTS student_info;

CREATE TABLE student_info(
	stud_id INT AUTO_INCREMENT,
	stud_code INT,
	stud_name VARCHAR(50),
	subject VARCHAR(50),
	marks INT,
	phone VARCHAR(50),
	primary key (stud_id));
	
show tables;

describe student_info;

INSERT INTO student_info(stud_code,stud_name,subject,marks,phone)
VALUES (101, "Mark", "English", 68, "34234345567"),
(102, "Joseph", "Physics", 70, "98123234345"),
(103, "Mary", "Maths", 72, "980985675645");

select * from student_info;

select stud_name, marks from student_info;

select * from student_info;

DELETE FROM student_info WHERE stud_name="Joseph";

select * from student_info;

UPDATE student_info
SET stud_name = "Rosalía"
WHERE stud_id = 1;

select * from student_info;


select * from student_info;









