drop table if exists tb_courses_users;

create table tb_courses_users(
    course_id uuid,
    user_id   uuid,

    primary key (course_id, user_id),
    foreign key (course_id) references tb_courses (id),
    foreign key (user_id) references tb_users (id)
);
