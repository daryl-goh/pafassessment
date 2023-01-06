-- CREATE ESHOP DATABASE
drop database if exists eshop;

create database eshop;

show databases;

use eshop;

-- CREATE CUSTOMERS TABLE

create table customers (
    name varchar(32),
    address varchar(128) not null,
    email varchar(128) not null,
	primary key (name)
);

-- INSERT CUSTOMERS DATA

insert into customers (name, address, email) values ('fred', '201 Cobblestone Lane', 'fredflintstone@bedrock.com')
insert into customers (name, address, email) values ('sherlock', '221B Baker Street, London', 'sherlock@consultingdetective.org')
insert into customers (name, address, email) values ('spongebob', '124 Conch Street, Bikini Bottom', 'spongebob@yahoo.com')
insert into customers (name, address, email) values ('jessica', '698 Candlewood Land, Cabot Cove', 'fletcher@gmail.com')
insert into customers (name, address, email) values ('dursley', '4 Privet Drive, Little Whinging, Surrey', 'dursley@gmail.com')

-- SELECT TABLES

select * from customers;


-- FIND CUSTOMER BY NAME

select name, address, email from customers where name = 'dursley';






-- CREATE ESTORE DATABASE

drop database if exists estore;

create database estore;

show databases;

use estore;

show tables;


-- CREATE ORDERS TABLE

create table orders (
    order_id int not null,
    delivery_id varchar(128) not null,
    name varchar(32) not null,
    address varchar(128) not null,
    email varchar(128) not null,
    status enum('pending','dispatch'),
    order_date datetime default current_timestamp ON UPDATE current_timestamp,
	primary key (order_id)
);


-- INSERT INTO ORDERS TABLE


insert into orders (order_id, delivery_id, name, address, email, status) values (12345678, 1, 'Tom', 'London 123', 'Tom@gmail.com', 'Processing')


-- DELETE FROM ORDERS TABLE

delete from orders where order_id = '12345678';




-- CREATE ORDER_STATUS TABLE

use eshop;

create table order_status (
	order_id int not null,
    delivery_id varchar(128) not null,
    status enum('pending','dispatch'),
    status_update datetime default current_timestamp ON UPDATE current_timestamp
);


-- INSERT INTO ORDERS STATUS TABLE

insert into order_status (order_id, delivery_id, status) values (12345678, 1, 'pending')
insert into order_status (order_id, delivery_id, status) values (12345678, 1, 'dispatch')


-- JOIN ORDER STATUS AND ORDERS TABLES

select eshop.order_status.status, estore.orders.name
from eshop.order_status
join estore.orders
on eshop.order_status.order_id = estore.orders.order_id
where estore.orders.name = Tom;