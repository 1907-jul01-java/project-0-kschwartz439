drop table if exists users;
drop table if exists userLogins;
drop table if exists accounts;
create table accounts (accountName varchar not null, accountNumber serial(9) primary key, userRef integer references userId not null, userRef2 integer references userId, accountBalance float(10, 2));
create table users (firstName varchar not null, lastName varchar not null, id integer primary key references userId, usersAccess varchar not null);
create table userLogins (username varchar primary key, userPass varchar not null, userId serial not null);

insert into creator values (p4ssw0rd, KellyCR);

--Get PL/SQL language to run this line
create or replace function get_username(user_name varchar) returns varchar AS
$$
    SELECT username FROM userLogins WHERE username = user_name;
$$language sql;

create or replace function get_userPass(user_pass varchar) returns varchar AS
$$
    SELECT id FROM userLogins WHERE userPass = user_pass;
$$language sql;

create or replace function get_accounts(userId) returns
$$ 
    select accountNumber from accounts where userRef = userId or userRef2 = userId;
$$language sql;