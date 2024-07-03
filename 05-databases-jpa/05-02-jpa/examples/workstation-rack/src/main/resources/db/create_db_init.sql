CREATE TYPE rack_status_enum AS ENUM ('ACTIVE', 'RETURNED', 'REPAIR', 'OUTDATED', 'BRICKED');

CREATE TABLE T_TEAM
(
    id   BIGINT DEFAULT random() PRIMARY KEY,
    name varchar(255) NOT NULL
);
CREATE SEQUENCE IF NOT EXISTS SEQ_TEAM_ID;

CREATE TABLE T_RACK
(
    id            BIGINT DEFAULT random() PRIMARY KEY,
    serial_number varchar(20)      NOT NULL UNIQUE,
    status        rack_status_enum NOT NULL,
    team_id       BIGINT           NOT NULL REFERENCES T_TEAM (id),
    assembled_at  DATE
);
CREATE SEQUENCE IF NOT EXISTS SEQ_RACK_ID;


