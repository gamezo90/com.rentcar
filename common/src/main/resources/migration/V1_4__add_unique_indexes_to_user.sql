alter table rentcar.users
    add unique (user_login);

alter table rentcar.users
    add unique (user_email);