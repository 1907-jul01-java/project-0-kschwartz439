drop table if exists customers;
drop table if exists employees;
drop table if exists admins;
drop table if exists accounts;
drop table if exists creator;
create table accounts (id int primary key, accountName varchar not null, accountNumber int not null);
create table customers (id serial primary key, firstName varchar not null, lastName varchar not null, accountId int not null, customerPass varchar not null);
create table employees (id serial primary key, firstName varchar not null, lastName varchar not null, employeeId varchar not null, employeePass varchar not null);
create table admins (id serial primary key, firstName varchar not null, lastName varchar not null, adminId varchar not null, adminPass varchar not null);
create table creator (id serial primary key, pass int not null, creatorId varchar not null);

--Get PL/SQL language to run this line
create or replace function get_admin_id(admin_user varchar) returns integer AS
$$
    SELECT id FROM admins WHERE admin_id = admin_user;
$$language sql;

create or replace function get_creator_id(creator_user varchar) returns integer AS
$$
    SELECT id FROM admins WHERE creator_id = creator_user;
$$language sql;

create or replace function get_employee_id(employee_user varchar) returns integer AS
$$
    SELECT id FROM admins WHERE employee_id = employee_user;
$$language sql;

create or replace function get_customer_id(customer_user varchar) returns integer AS
$$
    SELECT id FROM admins WHERE customer_id = customer_user;
$$language sql;