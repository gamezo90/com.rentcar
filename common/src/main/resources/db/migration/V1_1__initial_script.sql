create table if not exists rentcar.roles
(
    id                bigserial
        constraint roles_pk
            primary key,
    role_name         varchar(15) default 'USER'::character varying not null,
    creation_date     timestamp(6),
    modification_date timestamp(6)
);

alter table rentcar.roles
    owner to postgres;

create table if not exists rentcar.l_role_user
(
    id      bigserial
        constraint l_role_user_pk
            primary key,
    user_id bigint not null,
    role_id bigint not null
        constraint l_role_user_roles_id_fk
            references rentcar.roles
            on update cascade on delete cascade
);

alter table rentcar.l_role_user
    owner to postgres;

create unique index if not exists l_role_user_id_uindex
    on rentcar.l_role_user (id);

create index if not exists l_role_user_user_id_role_id_index
    on rentcar.l_role_user (user_id, role_id);

create unique index if not exists roles_id_uindex
    on rentcar.roles (id);

create table if not exists rentcar.users
(
    id                bigserial
        constraint users_pk
            primary key,
    user_name         varchar(20)  default 'name'::character varying        not null,
    surname           varchar(50)  default 'surname'::character varying     not null,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6)             not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6)             not null,
    is_banned         boolean      default false                            not null,
    is_deleted        boolean      default false                            not null,
    region            varchar(30)  default 'some region'::character varying not null,
    birthday          timestamp(6) default CURRENT_TIMESTAMP(6)             not null,
    gender            varchar(15),
    user_login        varchar,
    user_password     varchar
);

alter table rentcar.users
    owner to postgres;

create table if not exists rentcar.cars
(
    id                  bigserial
        constraint cars_pk
            primary key,
    manufacturer        varchar(20)                                           not null,
    model               varchar(20)                                           not null,
    year_of_manufacture timestamp(6)                                          not null,
    engine_volume       real                                                  not null,
    color               varchar(15)                                           not null,
    conditioner         boolean      default false                            not null,
    registration_number varchar(10)                                           not null,
    price               double precision                                      not null,
    user_id             bigint
        constraint cars_users_id_fk
            references rentcar.users
            on update cascade on delete cascade,
    photo               varchar(255),
    region              varchar(30)  default 'some region'::character varying not null,
    creation_date       timestamp(6) default CURRENT_TIMESTAMP(6),
    modification_date   timestamp(6) default CURRENT_TIMESTAMP(6)
);

alter table rentcar.cars
    owner to postgres;

create unique index if not exists cars_id_uindex
    on rentcar.cars (id);

create table if not exists rentcar.discount_system
(
    id                bigserial
        constraint discount_system_pk
            primary key,
    user_id           bigint                                    not null
        constraint discount_system_users_id_fk
            references rentcar.users
            on update cascade on delete cascade,
    discount_size     real         default 0                    not null,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    expiration_date   timestamp(6) default CURRENT_TIMESTAMP(6) not null
);

alter table rentcar.discount_system
    owner to postgres;

create unique index if not exists discount_system_id_uindex
    on rentcar.discount_system (id);

create unique index if not exists discount_system_user_id_uindex
    on rentcar.discount_system (user_id);

create unique index if not exists discount_system_user_id_uindex_2
    on rentcar.discount_system (user_id);

create table if not exists rentcar.order_history
(
    id                bigserial
        constraint order_history_pk
            primary key,
    user_id           bigint                                    not null
        constraint order_history_users_id_fk
            references rentcar.users
            on update cascade on delete cascade,
    car_id            bigint                                    not null
        constraint order_history_cars_id_fk
            references rentcar.cars
            on update cascade on delete cascade,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    expiration_date   timestamp(6) default CURRENT_TIMESTAMP(6) not null
);

alter table rentcar.order_history
    owner to postgres;

create unique index if not exists order_history_id_uindex
    on rentcar.order_history (id);

create unique index if not exists users_id_uindex
    on rentcar.users (id);

