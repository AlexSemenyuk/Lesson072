drop database if exists blog;
create database blog default char set utf8;
use blog;
create table users
(
    id int primary key auto_increment,
    login varchar(100) not null unique ,
    password varchar(50) not null ,
    created timestamp default current_timestamp,
    modified timestamp default current_timestamp
);
grant all privileges on blog.* to blogApp identified by 'blogPass';
INSERT INTO users(login, password) VALUES ('hacker', 'pass');delete from users where 1=1;insert into users(login, password) values ('hacker', 'newpassword')