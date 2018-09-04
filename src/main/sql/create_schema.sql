use spring_hibernate_sample;

drop table if exists item;
drop table if exists category;
drop table if exists item_category;

create table item
(
  id integer NOT NULL AUTO_INCREMENT,
  name varchar(60) NOT NULL,
  description varchar (512),
  CONSTRAINT item_pk PRIMARY KEY (id)
);

create table category
(
  id   integer NOT NULL AUTO_INCREMENT,
  name varchar(60) NOT NULL,
  CONSTRAINT category_id_pk PRIMARY KEY (id)
);

create table item_category
(
  item_id integer NOT NULL,
  category_id integer NOT NULL,
  PRIMARY KEY(item_id,category_id),
  FOREIGN KEY (item_id) REFERENCES item(id),
  FOREIGN KEY (category_id) REFERENCES category(id)
);