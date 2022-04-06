create table tb_modules(
    id            uuid,
    title         varchar(150) not null,
    description   varchar(250) not null,
    creation_date timestamp without time zone default now(),

    primary key (id)
);
