SELECT *
FROM T_RACK;

SELECT serial_number, team_id
FROM T_RACK;

SELECT serial_number, team_id
FROM T_RACK
WHERE default_location = 'Porto';

SELECT serial_number, team_id
FROM T_RACK
WHERE serial_number LIKE '1000%';

SELECT 'Rack with serial number '
           || serial_number || ' has the default location '
           || default_location || ' and is assigned to team '
           || team_id
FROM T_RACK
WHERE (created_at >= '2024-02-01' AND created_at <= '2024-02-29')
   OR (created_at >= '2024-04-01' AND created_at <= '2024-04-30');

------------

SELECT DISTINCT default_location
FROM T_RACK;

SELECT *
FROM T_RACK LIMIT 3;

SELECT *
FROM T_RACK LIMIT 3
OFFSET 2;

SELECT *
FROM T_RACK
WHERE team_id IN (SELECT T_TEAM.id
                  FROM T_TEAM
                  WHERE T_TEAM.name LIKE 'A%');

SELECT default_location, count(*) as Quantity
FROM T_RACK
GROUP BY default_location;

SELECT default_location, count(*) as Quantity
FROM T_RACK
GROUP BY default_location
HAVING count(*) > 3;
