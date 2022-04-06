alter table tb_lessons
    add column module_id uuid, add foreign key (module_id) references tb_modules (id);
