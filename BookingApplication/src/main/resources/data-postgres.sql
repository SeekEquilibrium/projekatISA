--ROLES
insert into role(id, name) values (nextval('role_id_seq'), 'COTTAGE_OWNER');
insert into role(id, name) values (nextval('role_id_seq'), 'CLIENT');
insert into role(id, name) values (nextval('role_id_seq'), 'BOAT_OWNER');

--Users (password123 for everyone)
insert into user_app(id, email, name, password, phone_number, role_id, surname, username) values
    (nextval('user_app_seq'), 'srdjan@gmail.com', 'Srdjan', '$2a$10$SFp508WvAPKDbemvKcYdd.wLahgUcoBDOPjBRXgNMzDBe3ot/ElwG', '2131231', 1, 'Beric', 'srdjan');
    insert into cottage_owner(id) values (1);

insert into user_app(id, email, name, password, phone_number, role_id, surname, username) values
    (nextval('user_app_seq'), 'boban@gmail.com', 'Boban', '$2a$10$SFp508WvAPKDbemvKcYdd.wLahgUcoBDOPjBRXgNMzDBe3ot/ElwG', '2131231', 2, 'Rajovic', 'boban');
    insert into client(id) values (2);

insert into user_app(id, email, name, password, phone_number, role_id, surname, username) values
    (nextval('user_app_seq'), 'pavle@gmail.com', 'Pavle', '$2a$10$SFp508WvAPKDbemvKcYdd.wLahgUcoBDOPjBRXgNMzDBe3ot/ElwG', '2131231', 3, 'Pavlovic', 'pavle');
    insert into boat_owner(id) values (3);

--Cottages
insert into cottage(id, name, address, description, room_number, bed_number, rules, cottage_owner_id, latitude, longitude) values
    (nextval('cottage_seq_gen'), 'Fruskac', 'Fruskogorska 23',
    'Fruskac je jedan od najlepsih apartmana, bla bla...', 2, 4, 'Zabranjeno pusenje u zatvorenom prostoru.', 1, 45.2063777680445, 19.809699206259737);
insert into cottage(id, name, address, description, room_number, bed_number, rules, cottage_owner_id, latitude, longitude) values
    (nextval('cottage_seq_gen'), 'Fruskac 2', 'Fruskogorska 24',
    'Fruskac je jedan od najlepsih apartmana, bla bla...', 2, 4, 'Zabranjeno pusenje u zatvorenom prostoru.', 1, 45.45414088121907, 19.618685337374817);
    --Adding images of cottage to cottage
    insert into cottage_image(id, path, cottage_id) values (nextval('image_seq_gen'), 'Fruskac_7c131505-1f1d-4536-8719-c3dad38a091d.png', 1);
    insert into cottage_image(id, path, cottage_id) values (nextval('image_seq_gen'), 'Fruskac_1053c4e8-edb2-4b6e-8ff2-960aadd93113.png', 1);
    insert into cottage_image(id, path, cottage_id) values (nextval('image_seq_gen'), 'Fruskac 2_84e6e972-2ced-4d66-8459-faded6e8447f.png', 2);
    insert into cottage_image(id, path, cottage_id) values (nextval('image_seq_gen'), 'Fruskac 2_16274272-8dbd-4113-95ca-5ffc23c7e872.png', 2);
    insert into cottage_image(id, path, cottage_id) values (nextval('image_seq_gen'), 'Fruskac 2_bce314e6-d30b-466a-9f16-d2377f11289a.png', 2);

--Boats
insert into boat(id, name, address, latitude, longitude, description, rules, boat_owner_id) values
    (nextval('boat_seq_gen'), 'Posejdon', 'Ribarsko ostrvo', 45.233220, 19.838043, 'Sportski gliser najbolji izbor za vase malisane...', 'Zabaranjeno skakanje sa glisera', 3);
    insert into boat_image(id, path, boat_id) values (nextval('image_seq_gen'), 'Posejdon_5514913a-4d40-4164-90e2-516222b93571', 1);
    insert into boat_image(id, path, boat_id) values (nextval('image_seq_gen'), 'Posejdon_a6f12b5d-2099-4945-8976-28eae5e29e50', 1);


--Create Reservation
insert into cottage_reservations(id, date_start, date_end, status, cottage_id, client_id) values (nextval('reservation_seq_gen'), '2022-05-16', '2022-05-18', 'RESERVED', 1, 2);
insert into cottage_reservations(id, date_start, date_end, status, cottage_id, client_id) values (nextval('reservation_seq_gen'), '2020-08-16', '2020-08-18', 'RESERVED', 1, 2);
insert into cottage_reservations(id, date_start, date_end, status, cottage_id, client_id) values (nextval('reservation_seq_gen'), '2021-06-16', '2021-06-18', 'RESERVED', 1, 2);
insert into cottage_reservations(id, date_start, date_end, status, cottage_id, client_id) values (nextval('reservation_seq_gen'), '2021-08-16', '2021-08-18', 'RESERVED', 1, 2);
insert into cottage_reservations(id, date_start, date_end, status, cottage_id, client_id) values (nextval('reservation_seq_gen'), '2022-02-16', '2022-02-18', 'RESERVED', 1, 2);
insert into cottage_reservations(id, date_start, date_end, status, cottage_id, client_id) values (nextval('reservation_seq_gen'), '2022-08-16', '2022-08-18', 'RESERVED', 1, 2);

--Available appointments
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type, client_id) values (nextval('free_appointment_seq_gen'), '2022-05-17', false, 1, 25, 'RESERVED', 2);
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type, client_id) values (nextval('free_appointment_seq_gen'), '2022-05-18', false, 1, 25, 'RESERVED', 2);
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type, client_id) values (nextval('free_appointment_seq_gen'), '2022-05-16', false, 1, 25, 'RESERVED', 2);


insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type, client_id) values (nextval('free_appointment_seq_gen'), '2020-08-17', false, 1, 15, 'RESERVED', 2);
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type, client_id) values (nextval('free_appointment_seq_gen'), '2020-08-18', false, 1, 15, 'RESERVED', 2);
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type, client_id) values (nextval('free_appointment_seq_gen'), '2020-08-16', false, 1, 15, 'RESERVED', 2);

insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type, client_id) values (nextval('free_appointment_seq_gen'), '2021-06-17', false, 1, 11, 'RESERVED', 2);
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type, client_id) values (nextval('free_appointment_seq_gen'), '2021-06-18', false, 1, 11, 'RESERVED', 2);
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type, client_id) values (nextval('free_appointment_seq_gen'), '2021-06-16', false, 1, 11, 'RESERVED', 2);

insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type, client_id) values (nextval('free_appointment_seq_gen'), '2022-02-17', false, 1, 11, 'RESERVED', 2);
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type, client_id) values (nextval('free_appointment_seq_gen'), '2022-02-18', false, 1, 11, 'RESERVED', 2);
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type, client_id) values (nextval('free_appointment_seq_gen'), '2022-02-16', false, 1, 11, 'RESERVED', 2);

insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type, client_id) values (nextval('free_appointment_seq_gen'), '2021-08-17', false, 1, 10, 'RESERVED', 2);
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type, client_id) values (nextval('free_appointment_seq_gen'), '2021-08-18', false, 1, 10, 'RESERVED', 2);
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type, client_id) values (nextval('free_appointment_seq_gen'), '2021-08-16', false, 1, 10, 'RESERVED', 2);

insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-07-17', false, 1, 10, 'AVAILABLE');
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type, client_id) values (nextval('free_appointment_seq_gen'), '2022-08-17', false, 1, 10, 'RESERVED', 2);
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type, client_id) values (nextval('free_appointment_seq_gen'), '2022-08-18', false, 1, 10, 'RESERVED', 2);
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type, client_id) values (nextval('free_appointment_seq_gen'), '2022-08-16', false, 1, 10, 'RESERVED', 2);
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-08-19', false, 1, 10, 'AVAILABLE');
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-08-20', false, 1, 10, 'AVAILABLE');
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-08-21', false, 1, 10, 'AVAILABLE');
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-08-22', false, 1, 10, 'AVAILABLE');
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-08-23', false, 1, 10, 'AVAILABLE');
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-08-24', false, 1, 10, 'AVAILABLE');
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-08-25', false, 1, 10, 'AVAILABLE');
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-08-26', false, 1, 10, 'AVAILABLE');
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-08-27', false, 1, 10, 'AVAILABLE');
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-08-28', false, 1, 10, 'AVAILABLE');
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-08-29', false, 1, 10, 'AVAILABLE');
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-08-30', false, 1, 10, 'AVAILABLE');
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-08-31', false, 1, 10, 'AVAILABLE');
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-09-01', false, 1, 10, 'AVAILABLE');
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-09-02', false, 1, 10, 'AVAILABLE');
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-09-03', false, 1, 10, 'AVAILABLE');
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-09-04', false, 1, 10, 'AVAILABLE');
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-09-05', false, 1, 10, 'AVAILABLE');
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-09-06', false, 1, 10, 'AVAILABLE');
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-09-07', true, 1, 10, 'AVAILABLE');
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-09-08', true, 1, 10, 'AVAILABLE');
insert into appointment_cottage(id, date, has_action, cottage_id, price_per_day, type) values (nextval('free_appointment_seq_gen'), '2022-09-09', true, 1, 10, 'AVAILABLE');