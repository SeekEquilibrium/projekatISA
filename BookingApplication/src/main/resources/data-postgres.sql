--ROLES
insert into role(id, name) values (nextval('role_id_seq'), 'COTTAGE_OWNER');
insert into role(id, name) values (nextval('role_id_seq'), 'CLIENT');

--Users (password123 for everyone)
insert into user_app(id, email, name, password, phone_number, role_id, surname, username) values
    (nextval('user_app_seq'), 'srdjan@gmail.com', 'Srdjan', '$2a$10$SFp508WvAPKDbemvKcYdd.wLahgUcoBDOPjBRXgNMzDBe3ot/ElwG', '2131231', 1, 'Beric', 'srdjan');
    insert into cottage_owner(id) values (1);

insert into user_app(id, email, name, password, phone_number, role_id, surname, username) values
    (nextval('user_app_seq'), 'boban@gmail.com', 'Boban', '$2a$10$SFp508WvAPKDbemvKcYdd.wLahgUcoBDOPjBRXgNMzDBe3ot/ElwG', '2131231', 2, 'Rajovic', 'boban');
    insert into client(id) values (2);
--Cottages
insert into cottage(id, name, address, description, room_number, bed_number, rules, cottage_owner_id) values
    (nextval('cottage_seq_gen'), 'Fruskac', 'Fruskogorska 23',
    'Fruskac je jedan od najlepsih apartmana, bla bla...', 2, 4, 'Zabranjeno pusenje u zatvorenom prostoru.', 1);
    --Adding images of cottage to cottage
    insert into cottage_image(id, path, cottage_id) values (nextval('image_seq_gen'), 'Fruskac_7c131505-1f1d-4536-8719-c3dad38a091d.png', 1);
    insert into cottage_image(id, path, cottage_id) values (nextval('image_seq_gen'), 'Fruskac_1053c4e8-edb2-4b6e-8ff2-960aadd93113.png', 1);