DROP TABLE if exists employee_dtl;
DROP TABLE if exists AUTHORITIES;
DROP TABLE if exists USERS;

create table employee_dtl (
	id varchar(255) not null, 
	email varchar(255), 
	name varchar(255), 
	primary key (id)
);

create table users (
    username varchar(50) not null primary key,
    password varchar(50) not null,
    enabled boolean not null
);

create table authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);