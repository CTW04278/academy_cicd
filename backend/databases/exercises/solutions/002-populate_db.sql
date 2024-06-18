INSERT INTO T_TEAM (id, name, product, default_location)
VALUES (1, 'Team 1', 'Product A', 'Porto'),
       (2, 'Team 2', 'Product A', 'Porto'),
       (3, 'Team 3', 'Product B', 'Braga'),
       (4, 'Team 4', 'Product C', 'Lisbon'),
       (5, 'Team 5', 'Product C', 'Porto'),
       (6, 'Team 6', 'Product D', 'Braga'),
       (7, 'Team 7', 'Product D', 'Lisbon');


INSERT INTO T_TEAM_MEMBER (id, team_id, ctw_id, name)
VALUES (1, 1, 'ctw10000', 'Ana'),
       (2, 1, 'ctw10001', 'Manuel'),
       (3, 2, 'ctw10002', 'John'),
       (4, 2, 'ctw10003', 'Diana'),
       (5, 3, 'ctw10004', 'Jorge'),
       (6, 4, 'ctw10005', 'Pedro'),
       (7, 5, 'ctw10006', 'Rita'),
       (8, 6, 'ctw10007', 'Nelson'),
       (9, 7, 'ctw10008', 'Bruno');



INSERT INTO T_RACK (id, serial_number, status, team_id, default_location, created_at)
VALUES (1, '1000-12021-01', 'Active', 1, 'Porto', '2024-02-01'),
       (2, '1000-12021-02', 'Active', 1, 'Braga', '2024-02-01'),
       (3, '1000-12021-03', 'Active', 2, 'Porto', '2024-03-01'),
       (4, '1000-12021-04', 'Active', 3, 'Porto', '2024-04-01'),
       (5, '1000-12021-05', 'Active', 4, 'Lisbon', '2024-01-01'),
       (6, '1000-12021-06', 'Active', 5, 'Lisbon', '2024-01-30'),
       (7, '1000-12021-07', 'Active', 6, 'Porto', now()),
       (8, '1000-12021-08', 'Active', 7, 'Porto', now());


INSERT INTO T_RACK_ASSET (id, asset_tag, rack_id)
VALUES (1, 'A-123-01', 1),
       (2, 'A-123-02', 1),
       (3, 'A-123-03', 1),
       (4, 'A-123-04', 2),
       (5, 'A-123-05', 2),
       (6, 'A-123-06', 3),
       (7, 'A-123-07', 3),
       (8, 'A-123-08', 4),
       (9, 'A-123-09', 5),
       (10, 'A-123-10', 6),
       (11, 'A-123-11', 6),
       (12, 'A-123-12', 7),
       (13, 'A-123-13', 7),
       (14, 'A-123-14', 7),
       (15, 'A-123-15', 8),
       (16, 'A-123-16', 8);

CREATE SEQUENCE seq_booking;
INSERT INTO T_BOOKING (id, rack_id, requester_id, book_from, book_to, created_at)
VALUES (nextval('seq_booking'), 1, 1, '2024-01-02', '2024-01-04', '2024-01-01'),
       (nextval('seq_booking'), 1, 1, '2024-01-10', '2024-01-12', '2024-01-09'),
       (nextval('seq_booking'), 1, 2, '2024-01-08', '2024-01-09', '2024-01-08'),
       (nextval('seq_booking'), 2, 2, '2024-01-10', '2024-01-12', '2024-01-08'),
       (nextval('seq_booking'), 3, 2, '2024-06-24', '2024-07-10', '2024-04-16');
