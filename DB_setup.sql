/*resets database by nuking any existing ones*/
drop table trainer cascade constraints;
drop table pokemonName cascade constraints;
drop table pokemonBelongs cascade constraints;
drop table professorTrades cascade constraints;
drop table healPokemon cascade constraints;
drop table nurse cascade constraints;
drop table hospital cascade constraints;
drop table incubation cascade constraints;
drop table incubator cascade constraints;
drop table eggDeposit cascade constraints;

Create table trainer(
	trainer_id int not null PRIMARY KEY,
	trainer_name varchar(55) not null);

Create table pokemonName(
	pid int not null PRIMARY KEY,
	pName varchar(55) not null,
	cp int,
	hp int,
	pokeball varchar(55));

Create table pokemonBelongs(
	pid int not null,
	trainer_id int, 
	FOREIGN KEY(trainer_id) references trainer,
	FOREIGN KEY(pid) references pokemonName 
		ON DELETE CASCADE);

Create table professorTrades(
	professor_name varchar(55) not null
,
	pid int,
	trainer_id int,
	PRIMARY KEY(professor_name, pid, trainer_id),
	FOREIGN KEY(pid) references pokemonName 
		ON DELETE CASCADE,
	FOREIGN KEY(trainer_id) references trainer 
		ON DELETE CASCADE);

Create table nurse(
	nid int not null PRIMARY KEY,
	hname varchar(55));

Create table hospital(
	hname varchar(55) not null PRIMARY KEY,
	nid int not null,
	FOREIGN KEY(nid) references nurse
		ON DELETE CASCADE);

ALTER TABLE nurse
ADD CONSTRAINT fk_hospital
	FOREIGN KEY(hname) references hospital;

CREATE or REPLACE TRIGGER nurse_work
	BEFORE update on nurse
	FOR EACH ROW 
	BEGIN
	if :new.hname<>:old.hname
	then
	update hospital h
		set h.hname = :new.hname
		where h.nid = :new.nid;		
	end if;
	END;
/



create table healPokemon(
	pid int not null,
	nid int not null,
	FOREIGN KEY(pid)
		references pokemonName
		ON DELETE CASCADE,
	FOREIGN KEY(nid)
		references nurse
		ON DELETE CASCADE);

Create table Incubator(
	iid int not null PRIMARY KEY,
	pid int,
 	FOREIGN KEY(pid) references pokemonName
		ON DELETE CASCADE);

Create table Incubation(
	nid int NOT NULL,
	iid int NOT NULL,
	pid int NOT NULL, 
	FOREIGN KEY(nid) references Nurse
		ON DELETE CASCADE,
	FOREIGN KEY(iid) references Incubator
		ON DELETE CASCADE,
	FOREIGN KEY(pid) references pokemonName
		ON DELETE CASCADE);


Create table eggDeposit( 
	Eddate TIMESTAMP not null PRIMARY KEY,
	Pid int not null,
	trainer_id int not null,
	FOREIGN KEY(pid) references pokemonName
		ON DELETE CASCADE,
	FOREIGN KEY(trainer_id) references trainer
		ON DELETE CASCADE);
