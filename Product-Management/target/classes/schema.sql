

create table App_Product (id int Auto_increment, name varchar(255), make varchar(255),model varchar(255),cost int,created_Date Date);
CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;

insert into App_Product (name,make,model,cost,created_Date) values('Shirt','Cotton','CT001',699,'2022-08-19');
