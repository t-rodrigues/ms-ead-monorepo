create table tb_lessons(
    id            uuid,
    title         varchar(150) not null,
    description   varchar(250) not null,
    video_url     varchar(250),
    creation_date timestamp without time zone default now(),

    primary key (id)
);
