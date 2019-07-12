drop table if exists customers;
drop table if exists employees;
drop table if exists admins;
drop table if exists accounts;
drop table if exists creator;
create table accounts (accountName varchar not null, accountNumber int not null primary key);
create table customers (firstName varchar not null, lastName varchar not null, customerId varchar not null primary key, customerPass varchar not null);
create table employees (firstName varchar not null, lastName varchar not null, employeeId varchar not null primary key, employeePass varchar not null);
create table admins (firstName varchar not null, lastName varchar not null, adminId varchar not null primary key, adminPass varchar not null);
create table creator (createrPass varchar not null, creatorId varchar not null primary key);

insert into creator values (p4ssw0rd, KellyCR);

--Get PL/SQL language to run this line
create or replace function get_admin_id(admin_user varchar) returns varchar AS
$$
    SELECT id FROM admins WHERE adminId = admin_user;
$$language sql;

create or replace function get_admin_pass(admin_pass varchar) returns varchar AS
$$
    SELECT id FROM admins WHERE adminPass = admin_pass;
$$language sql;

create or replace function get_creator_id(creator_user varchar) returns varchar AS
$$
    SELECT id FROM admins WHERE creatorId = creator_user;
$$language sql;

create or replace function get_creator_pass(creator_pass varchar) returns varchar AS
$$
    SELECT id FROM admins WHERE creatorPass = creator_pass;
$$language sql;

create or replace function get_employee_id(employee_user varchar) returns varchar AS
$$
    SELECT id FROM admins WHERE employeeId = employee_user;
$$language sql;

create or replace function get_employee_pass(employee_pass varchar) returns varchar AS
$$
    SELECT id FROM admins WHERE employeePass = employee_pass;
$$language sql;

create or replace function get_customer_id(customer_user varchar) returns varchar AS
$$
    SELECT id FROM admins WHERE customerId = customer_user;
$$language sql;

create or replace function get_customer_pass(customer_pass varchar) returns varchar AS
$$
    SELECT id FROM admins WHERE customerPass = customer_pass;
$$language sql;