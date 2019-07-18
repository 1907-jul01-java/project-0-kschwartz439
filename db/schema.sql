drop table if exists usersAccounts;
drop table if exists users;
drop table if exists accounts;
drop table if exists userLogins;
create table userLogins (username varchar unique not null, userPass varchar not null, userId serial primary key);
create table accounts (accountName varchar not null, accountNumber serial primary key, accountBalance numeric(10, 2) not null, approved boolean not null);
create table users (firstName varchar not null, lastName varchar not null, id integer primary key references userLogins(userId), access varchar not null);
create table usersAccounts (userRef integer references userLogins(userId), accountRef integer references accounts(accountNumber));

insert into userLogins(username, userPass) values ('KellyCR', 'password');
insert into users(firstName, lastName, id, access) values ('Kelly', 'Schwartz', 1, 'creator');
insert into userLogins(username, userPass) values ('KellyAD', 'p4ssw0rd');
insert into users(firstName, lastName, id, access) values ('Kelly2', 'Schwartz', 2, 'admin');
insert into userLogins(username, userPass) values ('KellyEM', 'password');
insert into users(firstName, lastName, id, access) values ('Kelly3', 'Schwartz', 3, 'employee');
insert into userLogins(username, userPass) values ('KellyCU', 'password');
insert into users(firstName, lastName, id, access) values ('Kelly4', 'Schwartz', 4, 'customer');


--Get PL/SQL language to run this line
/*
create or replace function get_username(user_name varchar) returns varchar AS
$$
    SELECT username FROM userLogins WHERE username = ?;
$$language sql

create or replace function get_userPass(user_pass varchar) returns varchar AS
$$
    SELECT id FROM userLogins WHERE userPass = ?;
$$language sql
*/