alter table rentcar.l_role_user
    add constraint l_role_user_users_id_fk
        foreign key (user_id) references rentcar.users
            on update cascade on delete cascade;
