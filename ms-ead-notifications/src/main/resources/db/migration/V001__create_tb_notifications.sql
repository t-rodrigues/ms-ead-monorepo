create table tb_notifications(
    id                  uuid primary key,
    user_id             uuid         not null,
    title               varchar(100) not null,
    message             text         not null,
    creation_date       timestamp without time zone default now(),
    notification_status varchar(50)  not null
);
