drop database kubb;
create database kubb DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
grant usage on *.* to kubb@localhost identified by 'password';
grant all privileges on kubb.* to kubb@localhost;