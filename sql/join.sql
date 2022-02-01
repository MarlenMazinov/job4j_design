create table departments(
id serial primary key,
	name varchar(255)
);

create table emploers(
id serial primary key,
	name varchar(255),
	department_id int references departments(id)
);

insert into departments(name) values('Development'), ('Supplying'), ('Customer department');
insert into emploers(name, department_id) values('Igor', 1), ('Olga', 1), ('Evgene', 2), ('Petr', null); 


select * from departments left join emploers on departments.id = emploers.department_id;

select * from departments right join emploers on departments.id = emploers.department_id;

select * from departments full join emploers on departments.id = emploers.department_id;

select * from departments cross join emploers;

select * from departments left join emploers on departments.id = emploers.department_id where emploers.department_id is null;

select emploers.name, departments.name from departments 
left join emploers
on departments.id = emploers.department_id;
select emploers.name, departments.name from emploers 
right join departments
on departments.id = emploers.department_id;

create table teens(
id serial primary key,
name varchar(255),
gender varchar(255)
);

insert into teens(name, gender) values('Olga', 'female'), ('Masha', 'female'), ('Max', 'male'), ('Fedor', 'male');

select females.name, males.name, (females.name || males.name)
from teens females 
cross join teens males 
where females.gender != males.gender 