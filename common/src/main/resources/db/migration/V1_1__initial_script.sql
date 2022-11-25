create table if not exists roles
(
    id                bigserial
        constraint roles_pk
            primary key,
    role_name         varchar(15) default 'USER'::character varying not null,
    creation_date     timestamp(6),
    modification_date timestamp(6)
);

alter table roles
    owner to postgres;

create unique index roles_id_uindex
    on roles (id);

create table if not exists l_role_user
(
    id      bigserial
        constraint l_role_user_pk
            primary key,
    user_id bigint not null,
    role_id bigint not null
        constraint l_role_user_roles_id_fk
            references roles
            on update cascade on delete cascade
);

alter table l_role_user
    owner to postgres;

create unique index l_role_user_id_uindex
    on l_role_user (id);

create index l_role_user_user_id_role_id_index
    on l_role_user (user_id, role_id);

create table if not exists users
(
    id                bigserial
        constraint users_pk
            primary key,
    user_name         varchar(20)  default 'name'::character varying         not null,
    surname           varchar(50)  default 'surname'::character varying      not null,
    region            varchar(30)  default 'some region'::character varying  not null,
    birthday          timestamp(6) default CURRENT_TIMESTAMP(6)              not null,
    gender            varchar(15)  default 'NOT_SELECTED'::character varying not null,
    user_login        varchar                                                not null,
    user_password     varchar                                                not null,
    user_email        varchar                                                not null,
    is_banned         boolean      default false                             not null,
    is_deleted        boolean      default false                             not null,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6)              not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6)              not null

);

alter table users
    owner to postgres;

create unique index users_id_uindex
    on users (id);

create table if not exists cars
(
    id                  bigserial
        constraint cars_pk
            primary key,
    manufacturer        varchar(20)                                           not null,
    model               varchar(20)                                           not null,
    engine_volume       real                                                  not null,
    conditioner         boolean      default false                            not null,
    year_of_manufacture integer                                               not null,
    color               varchar(20)                                           not null,
    photo               varchar(255)                                          not null,
    region              varchar(30)  default 'some region'::character varying not null,
    price               double precision                                      not null,
    registration_number varchar(10)                                           not null,
    user_id             bigint                                                not null
        constraint cars_users_id_fk
            references users
            on update cascade on delete cascade,
    is_banned           boolean      default false                            not null,
    creation_date       timestamp(6) default CURRENT_TIMESTAMP(6),
    modification_date   timestamp(6) default CURRENT_TIMESTAMP(6)
);

alter table cars
    owner to postgres;

create unique index cars_id_uindex
    on cars (id);

create table if not exists discount_system
(
    id                bigserial
        constraint discount_system_pk
            primary key,
    user_id           bigint                                    not null
        constraint discount_system_users_id_fk
            references users
            on update cascade on delete cascade,
    discount_size     real         default 0                    not null,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    expiration_date   timestamp(6) default CURRENT_TIMESTAMP(6) not null
);

alter table discount_system
    owner to postgres;

create unique index discount_system_id_uindex
    on discount_system (id);

create unique index discount_system_user_id_uindex
    on discount_system (user_id);

create unique index discount_system_user_id_uindex_2
    on discount_system (user_id);

create table if not exists order_history
(
    id                bigserial
        constraint order_history_pk
            primary key,
    user_id           bigint                                    not null
        constraint order_history_users_id_fk
            references users
            on update cascade on delete cascade,
    car_id            bigint                                    not null
        constraint order_history_cars_id_fk
            references cars
            on update cascade on delete cascade,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    expiration_date   timestamp(6) default CURRENT_TIMESTAMP(6) not null
);

alter table order_history
    owner to postgres;

create unique index order_history_id_uindex
    on order_history (id);

