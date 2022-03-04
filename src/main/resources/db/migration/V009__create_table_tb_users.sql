create table tb_users(
    id          uuid primary key,
    email       varchar(200) not null unique,
    full_name   varchar(200) not null,
    user_status varchar(100) not null,
    user_type   varchar(100) not null,
    cpf         varchar(20) unique,
    image_url   varchar(200)
);

create table tb_courses_users(
    id        uuid primary key,
    course_id uuid,
    user_id   uuid,

    foreign key (course_id) references tb_courses (id),
    foreign key (user_id) references tb_users (id)
);
