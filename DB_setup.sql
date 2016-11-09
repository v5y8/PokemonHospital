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
	FOREIGN KEY(trainer_id) references trainer(trainer_id) ON DELETE CASCADE
	FOREIGN KEY(pid) references pokemonName(pid) ON DELETE CASCADE);

Create table professorTrades(
	professor_name varchar(55) not null PRIMARY KEY,
	pid int,
	trainer_id int,
	FOREIGN KEY(pid) references pokemonName(pid) ON DELETE CASCADE,
	FOREIGN KEY(trainer_id) references trainer(trainer_id) ON DELETE CASCADE);

Create table healPokemon(
	pid int not null, 
	nid int not null,
	FOREIGN KEY(pid) references pokemonName(pid) ON DELETE CASCADE,
	FOREIGN KEY(nid) references nurse(nid) ON DELETE CASCADE);

Create table nurse(
	nid int not null PRIMARY KEY,
	hname varchar(55),
	FOREIGN KEY(hname) references Hospital(hname) ON DELETE CASCADE);


Create table hospital(
	hname varchar(55) not null PRIMARY KEY,
	nid int not null,
	FOREIGN KEY(nid) references nurse(nid) ON DELETE CASCADE);

Create table Incubation(
	nid int NOT NULL,
	iid int NOT NULL,
	pid int NOT NULL, 
	FOREIGN KEY(nid) references Nurse(nid) ON DELETE CASCADE,
	FOREIGN KEY(iid) references Incubator(iid),
	FOREIGN KEY(pid) references pokemonName(pid) ON DELETE CASCADE) ;

Create table Incubator(
	iid int not null PRIMARY KEY,
	pid int,
 	FOREIGN KEY(pid) references pokemonName(pid) ON DELETE CASCADE);

Create table eggDeposit( 
	Eddate int not null PRIMARY KEY,
	Pid int not null,
	trainer_id int not null,
	FOREIGN KEY(pid) references pokemonName(pid) ON DELETE CASCADE,
	FOREIGN KEY(trainer_id) references trainer(trainer_id) ON DELETE CASCADE);
