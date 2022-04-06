create table tb_users_roles(
    user_id uuid,
    role_id uuid,

    primary key (user_id, role_id),
    foreign key (user_id) references tb_users (id),
    foreign key (role_id) references tb_roles (id)
);
