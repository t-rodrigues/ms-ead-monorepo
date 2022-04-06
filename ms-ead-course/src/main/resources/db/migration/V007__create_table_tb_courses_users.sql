create table tb_courses_users(
    id        uuid,
    course_id uuid,
    user_id   uuid not null,

    primary key (id),
    foreign key (course_id) references tb_courses (id)
);
