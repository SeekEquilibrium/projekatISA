insert into user_app(id, name, surname, email, password, phone_number, user_type) values ( nextval('user_app_seq'), 'Srdjan', 'Beric', 'srdjan@gmail', '123', '12345678', 1 );

insert into cottage_owner (id) values (1);

insert into cottage (id, name, address, description, cottage_owner_id) values ( nextval('cottage_seq_gen'), 'Fruskac', 'Fruskogorska 23', 'Najbolje za vasu porodicu', 1);

insert into image_app (id, path) values (nextval('image_seq_gen'), 'cottage1.jpg');
insert into cottage_image (id, cottage_id) values (1, 1);
insert into image_app (id, path) values (nextval('image_seq_gen'), 'cottage2.jpg');
insert into cottage_image (id, cottage_id) values (2, 1);