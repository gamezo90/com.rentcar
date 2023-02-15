alter table rentcar.users
    alter column user_login type varchar(20) using user_login::varchar(20);
alter table rentcar.users
    alter column user_email type varchar(50) using user_email::varchar(50);