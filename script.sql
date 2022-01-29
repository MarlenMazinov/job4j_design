create table persons(
	id serial primary key,
    name text,
    phone bigserial,
	sex varchar(255),
	isMarried boolean
);
insert into persons(name, phone, sex, isMarried) values('Alexander', 79785562176, 'male', true);
update persons set name = 'Oleg';
delete from persons;