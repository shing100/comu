insert into users (email, USERNAME, PASSWORD, ACTIVE)
values ('admin@admin.com', 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 1);

insert into AUTHORITY (AUTHORITY_NAME) values ('ROLE_USER');
insert into AUTHORITY (AUTHORITY_NAME) values ('ROLE_ADMIN');

insert into USER_AUTHORITY (EMAIL, AUTHORITY_NAME) values ('admin@admin.com', 'ROLE_USER');
insert into USER_AUTHORITY (EMAIL, AUTHORITY_NAME) values ('admin@admin.com', 'ROLE_ADMIN');
