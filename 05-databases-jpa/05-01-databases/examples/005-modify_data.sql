INSERT INTO T_RACK (serial_number, team_id, default_location)
VALUES ('1000-12021-01', gen_random_uuid(), 'PORTO');

INSERT INTO T_RACK (serial_number, team_id, default_location, created_at)
VALUES ('1000-12021-02', gen_random_uuid(), 'PORTO', '2024-02-01');

UPDATE T_RACK
SET default_location='LISBON'
WHERE serial_number = '1000-12021-01';

DELETE
FROM T_RACK
WHERE serial_number = '2222-10000-01';
