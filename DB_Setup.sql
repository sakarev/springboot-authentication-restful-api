CREATE SCHEMA `protal` ;

use protal;

show tables;

create table users(
id int not null auto_increment,
first_name varchar(255) not null,
last_name varchar(255) null,
username varchar(255) not null,
passwprd varchar(255) not null,
primary key (id)

);

create table tokens(
id int not null auto_increment,
user_id varchar(255) not null,
token varchar(255) not null,
create_date DATETIME NOT NULL,
primary key (id)

);

insert into users values(0, "vasant", "sakre", "username", "password");
