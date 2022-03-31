insert into user_app(id, name, surname, email, password, phone_number, user_type) values ( nextval('user_app_seq'), 'Srdjan', 'Beric', 'srdjan@gmail', '123', '12345678', 1 );

insert into cottage (id, name, address, description) values ( nextval('cottage_seq_gen'), 'Fruskac', 'Fruskogorska 23', 'Najbolje za vasu porodicu');