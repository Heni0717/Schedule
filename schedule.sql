create table userInfo(
    id int auto_increment primary key ,
    name varchar(100) not null ,
    email varchar(255) not null ,
    created_at timestamp default current_timestamp ,
    updated_at timestamp default current_timestamp on update current_timestamp
);

create table schedule(
    id int auto_increment primary key ,
    task varchar(255) not null ,
    password varchar(255) not null ,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp on update current_timestamp,
    user_id int not null,
    constraint fk_user foreign key (user_id) references userInfo(id)
);