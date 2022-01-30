create table product(
    id serial primary key,
    name varchar(255),
    type_id int,
	expired_date date,
	price real
);

create table type(
	id serial primary key,
    name varchar(255)
);

insert into type(name) values('СЫР');
insert into type(name) values('МОЛОКО');
insert into type(name) values('МЯСО');

insert into product(name, type_id, expired_date, price) values('Сыр моцарелла', 1, '2022-02-10', 450.5);
insert into product(name, type_id, expired_date, price) values('Сыр плавленный', 1, '2022-01-29', 300.0);
insert into product(name, type_id, expired_date, price) values('Сыр ДорБлю', 1, '2022-02-05', 600.5);
insert into product(name, type_id, expired_date, price) values('Молоко Кубанское', 2, '2022-02-03', 100.5);
insert into product(name, type_id, expired_date, price) values('Молоко Главпродукт', 2, '2022-02-01', 95.2);
insert into product(name, type_id, expired_date, price) values('Молоко ВеселыйМолочник', 2, '2022-02-04', 90.0);
insert into product(name, type_id, expired_date, price) values('Мясо свиное мороженое', 3, '2022-01-25', 430.15);
insert into product(name, type_id, expired_date, price) values('Мясо говядина', 3, '2022-02-02', 450.5);
insert into product(name, type_id, expired_date, price) values('Мясо копченое', 3, '2022-02-09', 500.65);

select product.name
from product
join type
on product.type_id = type.id
where type.name = 'СЫР';

select product.name from product where name like '%мороженое%';

select product.name from product where expired_date < current_date;

select product.name
from product where price = (
select max(price)
	from product
);

select type.name, count(type.name)
from product
join type
on product.type_id = type.id
group by type.name;

select product.name
from product
join type
on product.type_id = type.id
where type.name = 'СЫР' or type.name = 'МОЛОКО';

select type.name
from product
join type
on product.type_id = type.id
group by type.name
having count(type.name) < 3;

select product.name, type.name
from product
join type
on product.type_id = type.id;