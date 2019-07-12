drop table if exists customers;
drop table if exists employees;
drop table if exists admins;
drop table if exists accounts;


create table accounts (id integer primary key, accountName varchar not null, accountNumber integer not null);
create table customers (id serial primary key, firstName varchar not null, lastName varchar not null, customerId int not null, customerPass varchar not null);
create table employees (id serial primary key, firstName varchar not null, lastName varchar not null, employeeId varchar not null, employeePass varchar not null);
create table admins (firstName varchar not null, lastName varchar not null, adminId varchar not null primary key, adminPass varchar not null);
create table creator (id serial primary key, pass int not null, creatorId varchar not null);

insert into admins values (Lilith, Schreve, a01, bunbuns);


create function get_adminId(admin_user as text) as integer as
$$
    select id from admins where adminId = admin_user;
$$ LANGUAGE sql;

create or replace function get_actor_id(actor_name text) returns integer as
$$
  select id from actors where name = actor_name;
$$ language sql;