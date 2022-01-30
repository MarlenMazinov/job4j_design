create table meal(
id serial primary key,
	name text
);
create table animal(
id serial primary key,
	name text,
	avg_age int,
	avg_weight real,
	meal_id int references meal(id)
);
insert into meal(name) values('Meat');
insert into meal(name) values('Herb');
insert into meal(name) values('Insects');
insert into animal(name, avg_age, avg_weight, meal_id) values('Tiger', 60, 300, 1);
insert into animal(name, avg_age, avg_weight, meal_id) values('Deer', 30, 100, 2);
insert into animal(name, avg_age, avg_weight, meal_id) values('Frog', 5, 0.2, 3);

select * from meal as m 
join animal as a 
on m.id = a.meal_id;

select a.name, a.avg_age, a.avg_weight, m.name 
from meal as m 
join animal as a 
on m.id = a.meal_id;

select a.name, m.name 
from meal as m 
join animal as a 
on m.id = a.meal_id;