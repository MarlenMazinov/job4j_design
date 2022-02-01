create table body(
id serial primary key,
name varchar(255)	
);

create table engine(
id serial primary key,
name varchar(255)	
);

create table transmission(
id serial primary key,
name varchar(255)	
);

create table car(
id serial primary key,
name varchar(255),
body_id int,
engine_id int,
transmission_id int
);

insert into body(name) values('Body#1'), ('Body#2'), ('Body#3');
insert into engine(name) values('Engine#1'), ('Engine#2'), ('Engine#3');
insert into transmission(name) values('Transmission#1'), ('Transmission#2'), ('Transmission#3');
insert into car(name, body_id, engine_id, transmission_id) values('Car#1', 1, 1, 1);
insert into car(name, body_id, engine_id, transmission_id) values('Car#2', 2, 3, 2);

select car.name, body.name, engine.name, transmission.name 
from car 
join body
on car.body_id = body.id
join engine
on car.engine_id = engine.id
join transmission
on car.transmission_id = transmission.id;

select body.name
from body 
left join car
on body.id = car.body_id where car.body_id is null;

select engine.name
from engine 
left join car
on engine.id = car.engine_id where car.engine_id is null;

select transmission.name
from transmission 
left join car
on transmission.id = car.transmission_id where car.transmission_id is null;