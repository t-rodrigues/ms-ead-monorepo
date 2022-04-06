create table tb_roles(
    id   uuid primary key,
    name varchar(100) not null unique
);

insert into tb_roles values (gen_random_uuid(), 'ROLE_ADMIN');
insert into tb_roles values (gen_random_uuid(), 'ROLE_INSTRUCTOR');
insert into tb_roles values (gen_random_uuid(), 'ROLE_STUDENT');
insert into tb_roles values (gen_random_uuid(), 'ROLE_USER');
