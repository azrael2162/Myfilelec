drop database if exists ecoleBenahmed;
create database ecoleBenahmed;
use ecoleBenahmed;

create table etudiant (
	idetudiant int(5) not null auto_increment,
	nom varchar(50),
	prenom varchar(50),
	email varchar(50),
	classe varchar(50),
	primary key (idetudiant)
);

create table user (
	login varchar (20) not null,
	mdp varchar (20) not null,
	droits enum ("admin", "user", "isert"),
	primary key (login)
);

insert into user values 
("admin","123","admin"),
("user","123","user");


insert into etudiant values 
(null,"Illan","Dylan","id@gmail.com","SIO JV"),
(null,"JB","Charles","JBC@gmail.com","SIO JV"),
(null,"Francis","Steven","FS@gmail.com","SIO JV");



