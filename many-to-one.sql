create table users(
    id serial primary key,
    name varchar(255)
);

create table orders(
    id serial primary key,
    name varchar(255),
   user_id int references users(id)
);