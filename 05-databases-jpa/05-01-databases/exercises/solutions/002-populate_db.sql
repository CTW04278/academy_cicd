INSERT INTO T_TEAM (id, name, product, default_location)
VALUES (nextval('SEQ_TEAM_ID'), 'Team 1', 'Product A', 'PORTO'),
       (nextval('SEQ_TEAM_ID'), 'Team 2', 'Product A', 'PORTO'),
       (nextval('SEQ_TEAM_ID'), 'Team 3', 'Product B', 'BRAGA'),
       (nextval('SEQ_TEAM_ID'), 'Team 4', 'Product C', 'LISBON'),
       (nextval('SEQ_TEAM_ID'), 'Team 5', 'Product C', 'PORTO'),
       (nextval('SEQ_TEAM_ID'), 'Team 6', 'Product D', 'BRAGA'),
       (nextval('SEQ_TEAM_ID'), 'Team 7', 'Product D', 'LISBON');


INSERT INTO T_TEAM_MEMBER (id, team_id, ctw_id, name)
VALUES (nextval('SEQ_TEAM_MEMBER_ID'), 1, 'ctw10000', 'Ana'),
       (nextval('SEQ_TEAM_MEMBER_ID'), 1, 'ctw10001', 'Manuel'),
       (nextval('SEQ_TEAM_MEMBER_ID'), 2, 'ctw10002', 'John'),
       (nextval('SEQ_TEAM_MEMBER_ID'), 2, 'ctw10003', 'Diana'),
       (nextval('SEQ_TEAM_MEMBER_ID'), 3, 'ctw10004', 'Jorge'),
       (nextval('SEQ_TEAM_MEMBER_ID'), 4, 'ctw10005', 'Pedro'),
       (nextval('SEQ_TEAM_MEMBER_ID'), 5, 'ctw10006', 'Rita'),
       (nextval('SEQ_TEAM_MEMBER_ID'), 6, 'ctw10007', 'Nelson'),
       (nextval('SEQ_TEAM_MEMBER_ID'), 7, 'ctw10008', 'Bruno');



INSERT INTO T_RACK (id, serial_number, status, team_id, default_location, created_at)
VALUES (nextval('SEQ_RACK_ID'), '1000-12021-01', 'Active', 1, 'PORTO', '2024-02-01'),
       (nextval('SEQ_RACK_ID'), '1000-12021-02', 'Active', 1, 'BRAGA', '2024-02-01'),
       (nextval('SEQ_RACK_ID'), '1000-12021-03', 'Active', 2, 'PORTO', '2024-03-01'),
       (nextval('SEQ_RACK_ID'), '1000-12021-04', 'Active', 3, 'PORTO', '2024-04-01'),
       (nextval('SEQ_RACK_ID'), '1000-12021-05', 'Active', 4, 'LISBON', '2024-01-01'),
       (nextval('SEQ_RACK_ID'), '1000-12021-06', 'Active', 5, 'LISBON', '2024-01-30'),
       (nextval('SEQ_RACK_ID'), '1000-12021-07', 'Active', 6, 'PORTO', now()),
       (nextval('SEQ_RACK_ID'), '1000-12021-08', 'Active', 7, 'PORTO', now());


INSERT INTO T_RACK_ASSET (id, asset_tag, rack_id)
VALUES (nextval('SEQ_RACK_ASSET_ID'), 'A-123-01', 1),
       (nextval('SEQ_RACK_ASSET_ID'), 'A-123-02', 1),
       (nextval('SEQ_RACK_ASSET_ID'), 'A-123-03', 1),
       (nextval('SEQ_RACK_ASSET_ID'), 'A-123-04', 2),
       (nextval('SEQ_RACK_ASSET_ID'), 'A-123-05', 2),
       (nextval('SEQ_RACK_ASSET_ID'), 'A-123-06', 3),
       (nextval('SEQ_RACK_ASSET_ID'), 'A-123-07', 3),
       (nextval('SEQ_RACK_ASSET_ID'), 'A-123-08', 4),
       (nextval('SEQ_RACK_ASSET_ID'), 'A-123-09', 5),
       (nextval('SEQ_RACK_ASSET_ID'), 'A-123-10', 6),
       (nextval('SEQ_RACK_ASSET_ID'), 'A-123-11', 6),
       (nextval('SEQ_RACK_ASSET_ID'), 'A-123-12', 7),
       (nextval('SEQ_RACK_ASSET_ID'), 'A-123-13', 7),
       (nextval('SEQ_RACK_ASSET_ID'), 'A-123-14', 7),
       (nextval('SEQ_RACK_ASSET_ID'), 'A-123-15', 8),
       (nextval('SEQ_RACK_ASSET_ID'), 'A-123-16', 8);

INSERT INTO T_BOOKING (id, rack_id, requester_id, book_from, book_to, created_at)
VALUES (nextval('SEQ_BOOKING_ID'), 1, 1, '2024-01-02', '2024-01-04', '2024-01-01'),
       (nextval('SEQ_BOOKING_ID'), 1, 1, '2024-01-10', '2024-01-12', '2024-01-09'),
       (nextval('SEQ_BOOKING_ID'), 1, 2, '2024-01-08', '2024-01-09', '2024-01-08'),
       (nextval('SEQ_BOOKING_ID'), 2, 2, '2024-01-10', '2024-01-12', '2024-01-08'),
       (nextval('SEQ_BOOKING_ID'), 3, 2, '2024-06-24', '2024-07-10', '2024-04-16');
