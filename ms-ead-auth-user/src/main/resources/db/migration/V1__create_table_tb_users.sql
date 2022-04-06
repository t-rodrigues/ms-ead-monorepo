create table tb_users(
    id                uuid,
    username          varchar(50)  not null unique,
    email             varchar(200) not null unique,
    password          varchar(100) not null,
    full_name         varchar(200) not null,
    user_status       varchar(100) not null,
    user_type         varchar(100) not null,
    phone_number      varchar(50),
    cpf               varchar(20)  not null unique,
    image_url         varchar(200),
    creation_date     timestamp without time zone default now(),
    last_updated_date timestamp without time zone default now(),

    primary key (id)
);
