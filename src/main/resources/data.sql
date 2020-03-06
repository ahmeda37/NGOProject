insert into addresses (address_id, address_1, city, zip, country) values
(1,'5648 Way RD','Edmonton','T5A 8F9','Canada'),
(2,'6854 Somewhere Pl','Calgary','T0A 9U2','Canada'),
(3,'1225 Heat St','Miami','55412','United States');

insert into users (user_id, address_id, first_name, last_name, email, hashed_password, admin) values
(1, 1, 'Abdurahman', 'Ali', 'aali@smpt.ca', 'afpagsggsgasd', 1),
(2, 2, 'Mohamed', 'Abdo', 'mabdo@smpt.ca', 'agsdsddgasgg', 0),
(3, 3, 'Bashir', 'Haji', 'bhaji@smpt.ca', 'asggdgsdgggs', 1);