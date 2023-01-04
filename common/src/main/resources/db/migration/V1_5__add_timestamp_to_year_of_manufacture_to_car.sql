alter table rentcar.cars
drop column year_of_manufacture;

alter table rentcar.cars
    add year_of_manufacture timestamp(6) default CURRENT_TIMESTAMP(6) not null;