create table cpu(
    id serial primary key,
    model varchar(255)
);

create table laptop(
    id serial primary key,
    model varchar(255),
    cpu_id int references cpu(id) unique
);