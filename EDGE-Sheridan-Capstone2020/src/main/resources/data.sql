insert into User (username, encryptedpassword, enabled)
values ('soar', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into User (username, encryptedpassword, enabled)
values ('rise', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
insert into User (username, encryptedpassword, enabled)
values ('basic', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into User (username, encryptedpassword, enabled)
values ('admin', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into Role (rolename)
values ('ROLE_ADMIN');
 
insert into Role (rolename)
values ('ROLE_BASIC');
insert into Role (rolename)
values ('ROLE_RISE');
 
insert into Role (rolename)
values ('ROLE_SOAR');


insert into user_roles (users_id, roles_id)
values (1, 1);
 
insert into user_roles (users_id, roles_id)
values (2, 2);
 
insert into user_roles (users_id, roles_id)
values (3, 3);
insert into user_roles (users_id, roles_id)
values (4, 4);

 

