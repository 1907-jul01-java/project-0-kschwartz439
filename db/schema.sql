drop table if exists users;
drop table if exists userLogins;
drop table if exists accounts;
drop table if exists usersAccounts;
create table accounts (accountName varchar not null, accountNumber serial primary key, accountBalance numeric(10, 2), id integer references userId, approved boolean);
create table users (firstName varchar not null, lastName varchar not null, id integer primary key references userId, access varchar not null);
create table userLogins (username varchar primary key, userPass varchar not null, userId serial not null);
create table usersAccounts (userRef references userId, accountRef references accountNumber)

insert into userLogins values (KellyCR, p4ssw0rd);
insert into users values (Kelly, Schwartz, SELECT userId FROM userLogins WHERE username = KellyCR, creator)

--Get PL/SQL language to run this line
create or replace function get_username(user_name varchar) returns varchar AS
$$
    SELECT username FROM userLogins WHERE username = user_name;
$$language sql;

create or replace function get_userPass(user_pass varchar) returns varchar AS
$$
    SELECT id FROM userLogins WHERE userPass = user_pass;
$$language sql;