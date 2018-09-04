use spring_hibernate_sample;

insert into category (id, name) values (1, 'books');
insert into category (id, name) values (2, 'toys');
insert into category (id, name) values (3, 'games');

insert into item (name,  description) values ('War and Peace','Classics');
insert into item (name,  description) values ('Finansist', 'Draiser');
insert into item (name,  description) values ('Warcraft', 'Strategies');
insert into item (name,  description) values ('Doom','Shooter');

insert into item_category (item_id, category_id) values (1, 1);
insert into item_category (item_id, category_id) values (2, 1);
insert into item_category (item_id, category_id) values (3, 3);
insert into item_category (item_id, category_id) values (4, 1);