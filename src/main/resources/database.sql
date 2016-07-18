drop table pessoas;
create table pessoas(
	id int primary key auto_increment,
    nome varchar(200),
    data_nascimento datetime
);


insert into pessoas values(null, "Rodrigo", '1983-03-27');
insert into pessoas values(null, "Luana", '1984-02-25');
insert into pessoas values(null, "Marley", '2012-08-23');
insert into pessoas values(null, "Isabela", '2015-04-30');