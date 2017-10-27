/**
 * CREATE Script for init of DB
 */

INSERT INTO users (user_id, username, password, enabled) VALUES
	('1', 'driver01', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', true),
	('2', 'driver02', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', true),
	('3', 'driver03', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', true);

-- Create 3 OFFLINE drivers

insert into driver (driver_id, date_created, deleted, online_status, username) values (1, now(), false, 'OFFLINE',
 'driver01');

insert into driver (driver_id, date_created, deleted, online_status, username) values (2, now(), false, 'OFFLINE',
 'driver02');

insert into driver (driver_id, date_created, deleted, online_status, username) values (3, now(), false, 'OFFLINE',
'driver03');


-- Create 3 ONLINE drivers

insert into driver (driver_id, date_created, deleted, online_status, username) values (4, now(), false, 'ONLINE',
 'driver04');

insert into driver (driver_id, date_created, deleted, online_status, username) values (5, now(), false, 'ONLINE',
 'driver05');

insert into driver (driver_id, date_created, deleted, online_status, username) values (6, now(), false, 'ONLINE',
 'driver06');

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (driver_id, longitude, latitude, date_coordinate_updated, date_created, deleted, online_status, username)
values
 (7,
 '9.5','55.954', now(), now(), false, 'OFFLINE',
 'driver07');

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (driver_id, longitude, latitude, date_coordinate_updated, date_created, deleted, online_status, username)
values
 (8,
 '9.5','55.954', now(), now(), false, 'ONLINE',
 'driver08');


-- Create manufacturer

insert into manufacturer (manufacturer_id, date_created, deleted, manufacturer_name) values (1, now(), false, 0);

insert into manufacturer (manufacturer_id, date_created, deleted, manufacturer_name) values (2, now(), false, 1);

insert into manufacturer (manufacturer_id, date_created, deleted, manufacturer_name) values (3, now(), false, 2);