create table tb_users_courses(
    id        uuid,
    user_id   uuid not null,
    course_id uuid not null,

    primary key (id),
    foreign key (user_id) references tb_users (id)
);
