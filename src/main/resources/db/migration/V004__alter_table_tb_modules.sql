alter table tb_modules
    add column course_id uuid, add foreign key (course_id) references tb_courses (id);
