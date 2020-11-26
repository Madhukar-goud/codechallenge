
insert into address_book(id, name) values (100, 'test');
insert into person(id, name, phone_num, addressbook_id) values (101, 'Name1', '0237588464', 100);
insert into person(id, name, phone_num, addressbook_id) values (102, 'Name2', '0137588465', 100);
insert into person(id, name, phone_num, addressbook_id) values (103, 'Name3', '0337588466', 100);
insert into person(id, name, phone_num, addressbook_id) values (104, 'Name4', '0437588467', 100);
insert into person(id, name, phone_num, addressbook_id) values (105, 'Name5', '0537588464', 100);

insert into address_book(id, name) values (101, 'test1');
insert into person(id, name, phone_num, addressbook_id) values (111, 'Name6', '0237588464', 101);
insert into person(id, name, phone_num, addressbook_id) values (112, 'Name2', '0137588464', 101);
insert into person(id, name, phone_num, addressbook_id) values (113, 'Name3', '0337588464', 101);
insert into person(id, name, phone_num, addressbook_id) values (114, 'Name4', '0437588464', 101);
insert into person(id, name, phone_num, addressbook_id) values (115, 'Name7', '0537588464', 101);