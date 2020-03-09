insert into addresses (address_id, address_1, city, zip, country) values
(1,'5648 Way RD','Edmonton','T5A 8F9','Canada'),
(2,'6854 Somewhere Pl','Calgary','T0A 9U2','Canada'),
(3,'1225 Heat St','Miami','55412','United States');

insert into users (user_id, address_id, first_name, last_name, email, hashed_password, admin) values
(1, 1, 'Abdurahman', 'Ali', 'aali@smpt.ca', '$2a$10$QoyPh5uXaW98PCECI6tPaOaQihKDHJ11TlSaE.xkiuimjJkUBkXJa', 1),
(2, 2, 'Mohamed', 'Abdo', 'mabdo@smpt.ca', '$2a$10$QoyPh5uXaW98PCECI6tPaOaQihKDHJ11TlSaE.xkiuimjJkUBkXJa', 0),
(3, 3, 'Bashir', 'Haji', 'bhaji@smpt.ca', 'asggdgsdgggs', 1);

insert into donation_types (donation_type_id, type_name, has_recurring_option) values
(1, 'Red Cross', 1),
(2, 'Childrens Hospital', 1),
(3, 'Goodwill', 0),
(4, 'Salvation Army', 0);

insert into carts(cart_id, user_id, cart_total, cart_processed) values
(1, 1, 150, 1),
(2, 2, 175, 1),
(3, 3, 300, 1),
(4, 1, 50, 0);

insert into gifts (gift_id, cart_id, donation_type_id, gift_amount, recurring, quantity) values
(1, 1, 1, 150, 1, 1),
(2, 2, 2, 175, 0, 1),
(3, 3, 3, 300, 0, 1),
(4, 4, 4, 50, 0, 1);