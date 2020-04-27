insert into employee_dtl (id, name, email) values ('1478ef23-16a3-4265-83d7-92076fa08ef2', 'siva', 'dummy1@nodomain.com');
insert into employee_dtl (id, name, email) values ('c9ac1e2c-cfdd-4719-ba94-c126d57dc035', 'prasad', 'dummy2@nodomain.com');
insert into employee_dtl (id, name, email) values ('5aa45a97-f363-490f-959d-43b633913dd6', 'somarouthu', 'dummy3@nodomain.com');


INSERT INTO users (username, password, enabled) values ('user','pass',true);
INSERT INTO users (username, password, enabled) values ('admin','pass',true);

INSERT INTO authorities (username,authority) values ('user','ROLE_USER');
INSERT INTO authorities (username,authority) values ('admin','ROLE_ADMIN');