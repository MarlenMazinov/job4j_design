create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);
insert into devices(name, price) values('Smartfone1', 15000);
insert into devices(name, price) values('Smartfone2', 25000);
insert into devices(name, price) values('Smartfone3', 40000);
insert into devices(name, price) values('Laptop1', 50000);
insert into devices(name, price) values('Laptop2', 70000);
insert into devices(name, price) values('Laptop3', 100000);
insert into devices(name, price) values('Smartwatch1', 2000);
insert into devices(name, price) values('Smartwatch2', 4000);
insert into devices(name, price) values('Smartwatch3', 6000);
insert into people(name) values('Vasiliy');
insert into people(name) values('Oleg');
insert into people(name) values('Mariya');
insert into devices_people(device_id, people_id) values(1, 1);
insert into devices_people(device_id, people_id) values(2, 2);
insert into devices_people(device_id, people_id) values(3, 3);
insert into devices_people(device_id, people_id) values(4, 1);
insert into devices_people(device_id, people_id) values(5, 2);
insert into devices_people(device_id, people_id) values(6, 3);
insert into devices_people(device_id, people_id) values(7, 1);
insert into devices_people(device_id, people_id) values(8, 2);
insert into devices_people(device_id, people_id) values(9, 3);

select avg(price) from devices;

select p.name, avg(d.price) 
from devices_people as dp
join people p
on dp.people_id = p.id
join devices d
on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price) 
from devices_people as dp
join people p
on dp.people_id = p.id
join devices d
on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;