create schema if not exists level_up;
use level_up;

create table if not exists level_up (
	level_up_id int(11) not null auto_increment primary key,
    customer_id int(11) not null,
    points int(11) not null,
    member_date date not null
);


create schema if not exists invoice;
use invoice;

create table if not exists invoice (
	invoice_id int(11) not null auto_increment primary key,
    customer_id int(11) not null,
    purchase_date date not null
);

create table if not exists invoice_item (
	invoice_item_id int(11) not null auto_increment primary key,
    invoice_id int(11) not null,
    inventory_id int(11) not null,
    quantity int(11) not null,
    unit_price decimal(7,2) not null    
);

alter table invoice_item add constraint fk_invoice_item_invoice foreign key (invoice_id) references invoice(invoice_id);

create schema if not exists inventory;
use inventory;

create table if not exists inventory (
	inventory_id int(11) not null auto_increment primary key,
    product_id int(11) not null,
    quantity int(11) not null
);
create schema if not exists product;
use product;

create table if not exists product (
	product_id int(11) not null auto_increment primary key,
    product_name varchar(50) not null,
    product_description varchar(255) not null,
    list_price decimal(7,2) not null,
    unit_cost decimal(7,2) not null
);
create schema if not exists customer;
use customer;

create table if not exists customer (
	customer_id int(11) not null auto_increment primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    street varchar(50) not null,
    city varchar(50) not null,
    zip varchar(10) not null,
    email varchar(75) not null,
    phone varchar(20) not null
);

create schema if not exists spring_security;
use spring_security;

create table if not exists users(
	username varchar(50) not null primary key,
	password varchar(100) not null,
	enabled boolean not null
);

create table if not exists authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username));
	create unique index ix_auth_username on authorities (username,authority
);

INSERT INTO users (username, password, enabled) VALUES
("admin", "$2a$10$XD9kqnAAdStj5BcAb53RP.XfNENuQg5iiZ0ftjxCG4rVr6BJkPZLa", true),
("manager", "$2a$10$VlB3ObdLwuwd/NGiq.3rj.xSqw9ezlo6SKYQI6kbN/LXugBy7uTl6", true),
("teamlead", "$2a$10$m8vwYtCsBTtA8M4UEQgzJe8U7Sn8.X/2jiIreEe2HLmR5CBxRh6IW", true),
("employee", "$2a$10$IOfTTl/L6oa/SwejHtaDQeYmt4oaGQluktg5viYhISyPj2v2OgnRe", true);

INSERT INTO authorities (username, authority) VALUES
("admin", "ROLE_ADMIN"),
("admin", "ROLE_MANAGER"),
("admin", "ROLE_TEAM_LEAD"),
("admin", "ROLE_EMPLOYEE"),
("manager", "ROLE_MANAGER"),
("manager", "ROLE_TEAM_LEAD"),
("manager", "ROLE_EMPLOYEE"),
("teamlead", "ROLE_TEAM_LEAD"),
("teamlead", "ROLE_EMPLOYEE"),
("employee", "ROLE_ EMPLOYEE");









