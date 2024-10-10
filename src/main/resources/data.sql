-- BUILDING --
INSERT INTO building(building_id, location, building_name, phone_contact) VALUES ('BD001','01 Khu CNC, Thu Duc','FPT WorkSpace','0924797913');
INSERT INTO building(building_id, location, building_name, phone_contact) VALUES ('BD002','100 Le Van Viet','FPT NVH','09204524');

-- ROOMTYPE --
INSERT INTO roomtype(id, room_type_name) VALUES ('RT001', 'Phòng đơn');
INSERT INTO roomtype(id, room_type_name) VALUES ('RT002', 'Phòng đôi');
INSERT INTO roomtype(id, room_type_name) VALUES ('RT003', 'Phòng họp');
INSERT INTO roomtype(id, room_type_name) VALUES ('RT004', 'Phòng sự kiện');


-- ROOM --
INSERT INTO room(room_id, creation_time, price, room_name, status, building_id, room_typeid) VALUES ('S001', '2024-10-09', 50, 'Phòng đơn 1', 'AVAILABLE', 'BD001', 'RT001');
INSERT INTO room(room_id, creation_time, price, room_name, status, building_id, room_typeid) VALUES ('S002', '2024-10-09', 55, 'Phòng đơn 2', 'AVAILABLE', 'BD001', 'RT001');
INSERT INTO room(room_id, creation_time, price, room_name, status, building_id, room_typeid) VALUES ('D001', '2024-10-09', 100, 'Phòng đôi 1', 'AVAILABLE', 'BD001', 'RT002');
INSERT INTO room(room_id, creation_time, price, room_name, status, building_id, room_typeid) VALUES ('D002', '2024-10-09', 105, 'Phòng đôi 2', 'AVAILABLE', 'BD001', 'RT002');
INSERT INTO room(room_id, creation_time, price, room_name, status, building_id, room_typeid) VALUES ('M001', '2024-10-09', 150, 'Phòng họp 1', 'AVAILABLE', 'BD001', 'RT003');
INSERT INTO room(room_id, creation_time, price, room_name, status, building_id, room_typeid) VALUES ('M002', '2024-10-09', 150, 'Phòng họp 2', 'AVAILABLE', 'BD002', 'RT003');
INSERT INTO room(room_id, creation_time, price, room_name, status, building_id, room_typeid) VALUES ('E001', '2024-10-09', 200, 'Phòng sự kiện 1', 'AVAILABLE', 'BD002', 'RT004');
INSERT INTO room(room_id, creation_time, price, room_name, status, building_id, room_typeid) VALUES ('E002', '2024-10-09', 200, 'Phòng sự kiện 2', 'AVAILABLE', 'BD002', 'RT004');



-- ITEMS --
INSERT INTO serviceitems(price, quantity, service_name, service_type, status) VALUES (10, 10, 'Ổ cắm', 'Thiết bị', 'AVAILABLE');
INSERT INTO serviceitems(price, quantity, service_name, service_type, status) VALUES (50, 10, 'Máy chiếu', 'Thiết bị', 'AVAILABLE');
INSERT INTO serviceitems(price, quantity, service_name, service_type, status) VALUES (30, 10, 'Bàn', 'Thiết bị', 'AVAILABLE');

-- TIMESLOT --
INSERT INTO timeslot(time_slot_id, status, time_start, time_end) VALUES (1, 'AVAILABLE', '7:00:00.000000', '10:00:00.000000');
INSERT INTO timeslot(time_slot_id, status, time_start, time_end) VALUES (2, 'AVAILABLE', '11:00:00.000000', '14:00:00.000000');
INSERT INTO timeslot(time_slot_id, status, time_start, time_end) VALUES (3, 'AVAILABLE', '15:00:00.000000', '18:00:00.000000');
INSERT INTO timeslot(time_slot_id, status, time_start, time_end) VALUES (4, 'AVAILABLE', '19:00:00.000000', '22:00:00.000000');
-- USER --
INSERT INTO user (user_id, password, role_name, username) VALUES ('OWN001', '$2a$10$923vpnosP9l1xDmLPXXph.3h5H4mUWmgSKSbXiEalfplWwpuyCraC', 'OWNER', 'owner1s' );
INSERT INTO user (user_id, password, role_name, username) VALUES ('CUS001', '$2a$10$9pBfv0jKNAdzV9ipro0jweN22CUDDFnMYCtwlNxxXHoqITqCuMUkm', 'CUSTOMER', 'cus01' );
INSERT INTO user (user_id, password, role_name, username) VALUES ('MA001', '$2a$10$9pBfv0jKNAdzV9ipro0jweN22CUDDFnMYCtwlNxxXHoqITqCuMUkm', 'MANAGER', 'ma01' );