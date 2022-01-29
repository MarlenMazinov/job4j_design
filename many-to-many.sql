create table users(
     id serial primary key,
     name varchar(255)
 );
 
 create table webservices(
     id serial primary key,
     name varchar(255)
 );
 
 create table users_webservices(
     id serial primary key,
     user_id int references users(id),
     webservice_id int references webservices(id)
 );