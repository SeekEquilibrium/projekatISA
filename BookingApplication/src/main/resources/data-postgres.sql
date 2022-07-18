--ROLES
insert into role(id, name) values (nextval('role_id_seq'), 'COTTAGE_OWNER');

--password123
insert into user_app(id, email, name, password, phone_number, role_id, surname, username) values
    (nextval('user_app_seq'), 'srdjan@gmail.com', 'Srdjan', '$2a$10$SFp508WvAPKDbemvKcYdd.wLahgUcoBDOPjBRXgNMzDBe3ot/ElwG', '2131231', 1, 'Beric', 'srdjan');
insert into cottage_owner(id) values (1);

insert into cottage(id, name, address, description, room_number, bed_number, rules, cottage_owner_id) values
    (nextval('cottage_seq_gen'), 'Fruskac', 'Fruskogorska 23',
    'Fruskac je jedan od najlepsih apartmana, bla bla...', 2, 4, 'Zabranjeno pusenje u zatvorenom prostoru.', 1);

insert into image_app(id, path) values (nextval('image_seq_gen'), 'Fruskac_7c131505-1f1d-4536-8719-c3dad38a091d.png');
insert into image_app(id, path) values (nextval('image_seq_gen'), 'Fruskac_1053c4e8-edb2-4b6e-8ff2-960aadd93113.png');

insert into cottage_image(id, cottage_id) values (1, 1);
insert into cottage_image(id, cottage_id) values (2, 1);
--insert into user_app(id, name, surname, email, password, phone_number, user_type) values ( nextval('user_app_seq'), 'Srdjan', 'Beric', 'srdjan@gmail', '123', '12345678', 1 );
--
--insert into cottage_owner (id) values (1);
--
--insert into cottage (id, name, address, description, room_number, bed_number, rules, cottage_owner_id)
--	values ( nextval('cottage_seq_gen'), 'Fruskac', 'Fruskogorska 23',
--	'Objekat Vila Davidovic-Fruska gora se nalazi u naselju Manđelos i nudi restoran, bar i vrt. Smeštaj je udaljen 33 km od Vrdnika, a gostima su na raspolaganju besplatan WiFi i privatni parking u okviru objekta.
--
--Dostupan je flat-screen TV sa satelitskim kanalima.
--
--Gosti ovog pansiona sa uslugom doručka mogu uživati u kontinentalnom doručku.
--
--Ako želite da istražite ovu oblast, okolina je pogodna za pešačenje.
--
--Šabac je udaljen 40 km od objekta Vila Davidovic-Fruska gora, dok se Sremska Mitrovica nalazi na 16 km. Najbliži aerodrom je Aerodrom Nikola Tesla u Beogradu, udaljen 62 km od smeštajnog objekta. Usluga prevoza od/do aerodroma je dostupna uz doplatu.'
--	, 2, 4, 'Zabranjeno pusenje u zatvorenom prostoru.', 1);
--
--insert into image_app (id, path) values (nextval('image_seq_gen'), 'cottage1.jpg');
--insert into cottage_image (id, cottage_id) values (1, 1);
--insert into image_app (id, path) values (nextval('image_seq_gen'), 'cottage2.jpg');
--insert into cottage_image (id, cottage_id) values (2, 1);
