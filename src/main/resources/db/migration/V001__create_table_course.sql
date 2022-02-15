create table tb_courses(
    id                uuid,
    name              varchar(150) not null,
    description       varchar(250) not null,
    course_status     varchar(50)  not null,
    course_level      varchar(50)  not null,
    user_instructor   uuid         not null,
    image_url         varchar(255),
    creation_date     timestamp without time zone default now(),
    last_updated_date timestamp without time zone default now(),

    primary key (id)
);
