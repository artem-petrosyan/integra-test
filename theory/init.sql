create table users
(
    id       serial,
    login    varchar,
    password varchar,
    role     varchar
);

insert into users (login, password, role)
values ('admin', '123', 'admin');
insert into users (login, password, role)
values ('root', 'QoeidmasASjs', 'admin');
insert into users (login, password, role)
values ('ivan', '12345', 'user');
insert into users (login, password, role)
values ('marina', 'qweqwe', 'user');
insert into users (login, password, role)
values ('sasha', 'asdASD', 'user');
