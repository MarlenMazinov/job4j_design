insert into role(name) values ('Customer');
insert into users(name, role_id) values ('Oleg', 1);
insert into rules(name) values ('Make order');
insert into role_rules(role_id, rules_id) values (1, 1);
insert into state(name) values ('Handled');
insert into category(name) values ('Express');
insert into item(name, user_id, category_id, state_id) values ('Item #1', 1, 1, 1);
insert into comments(name, item_id) values ('Delivery address', 1);
insert into attachs(name, item_id) values ('Check', 1);