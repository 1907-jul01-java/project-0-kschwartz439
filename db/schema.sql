drop table if exists customers;
drop table if exists employees;
drop table if exists admins;
drop table if exists accounts;
create table accounts (id int primary key, accountName varchar not null, accountNumber int not null);
create table customers (id serial primary key, firstName varchar not null, lastName varchar not null, birthday int not null, birthMonth int not null, birthYear int not null, accountId int not null, customerPass varchar not null);
create table employees (id serial primary key, firstName varchar not null, lastName varchar not null, birthday int not null, birthMonth int not null, birthYear int not null, employeeId varchar not null, employeePass varchar not null);
create table admins (id serial primary key, firstName varchar not null, lastName varchar not null, birthday int not null, birthMonth int not null, birthYear int not null, adminId varchar not null, adminPass varchar not null);
create table creator (id serial primary key, pass int not null, creatorId varchar not null);