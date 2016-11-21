Insert into trainer values(1,'Ash');
Insert into trainer values(2, 'Misty');
Insert into trainer values(3,'Brock');
Insert into trainer values(4,'Jesse');
Insert into trainer values(5,'James');
 
insert into pokemonname values(25,'Pikachu',342,40,'greatball');
insert into pokemonname values(132,'Ditto',119,20,'pokeball');
insert into pokemonname values(1,'Bulbasaur',283,29,'pokeball');
insert into pokemonname values(4,'Charmander',300,33,'ultraball');
insert into pokemonname values(149,'Dragonite',1248,90,'greatball');
Insert into pokemonname values(2,'Ivysaur',122,30,'pokeball');
Insert into pokemonname values(14,'Kakuna',100,44,'greatball');
Insert into pokemonname values(54,'Psyduck',129,32,'ultraball');
Insert into pokemonname values(61,'Polywhirl',340,21,'pokeball');
Insert into pokemonname values(78,'Rapidash',350,31,'greatball');
insert into pokemonname values(86,'Seel',403,45,'ultraball');
insert into pokemonname values(90,'Shellder',410,29,'pokeball');
insert into pokemonname values(94,'Gengar',230,43,'ultraball');
insert into pokemonname values(99,'Krabby',341,41,'greatball');
insert into pokemonname values(120,'Staryu',301,42,'ultraball');
 
 
insert into pokemonbelongs values (25,1);
insert into pokemonbelongs values (132,2);
insert into pokemonbelongs values (1,3);
insert into pokemonbelongs values (4,4);
insert into pokemonbelongs values (149,5);
 
insert into professortrades values('Oak',25,1);
insert into professortrades values('Willow',132,2);
insert into professortrades values('Elm',1,3);
insert into professortrades values('Birtch',4,4);
insert into professortrades values('Rowan',149,5);

insert into nurse values(1,'Kanto');
insert into nurse values(2,'Johto');
insert into nurse values(3,'Hoenn');
insert into nurse values(4,'Sinnoh');
insert into nurse values(5,'Unova');

insert into hospital values('Kanto',1);
insert into hospital values('Johto',2);
insert into hospital values('Hoenn',3);
insert into hospital values('Sinnoh',4);
insert into hospital values('Unova',5); 


ALTER TABLE nurse
ADD CONSTRAINT fk_hospital
        FOREIGN KEY(hname) references hospital;

 
insert into incubator values(1,86);
insert into incubator values(2,90);
insert into incubator values(3,94);
insert into incubator values(4,99);
insert into incubator values(5,120);
 
insert into incubation values(1,1,86);
insert into incubation values(2,2,90);
insert into incubation values(3,3,94);
insert into incubation values(4,4,99);
insert into incubation values(5,5,120);


insert into healpokemon values(2,1,current_timestamp);
insert into healpokemon values(14,2, current_timestamp);
insert into healpokemon values(54,3, current_timestamp);
insert into healpokemon values(61,4, current_timestamp);
insert into healpokemon values(78,5, current_timestamp);
 
insert into eggdeposit values(current_timestamp,86,1);
insert into eggdeposit values(current_timestamp,90,2);
insert into eggdeposit values(current_timestamp,94,3);
insert into eggdeposit values(current_timestamp,99,4);
insert into eggdeposit values(current_timestamp,120,5);
