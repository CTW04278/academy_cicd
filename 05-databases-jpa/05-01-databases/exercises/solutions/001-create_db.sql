DROP TABLE IF EXISTS T_RACK_ASSET;
DROP TABLE IF EXISTS T_BOOKING;
DROP TABLE IF EXISTS T_RACK;
DROP TABLE IF EXISTS T_TEAM_MEMBER;
DROP TABLE IF EXISTS T_TEAM;
DROP TYPE IF EXISTS rack_status_enum;


CREATE TYPE rack_status_enum AS ENUM ('Active', 'Returned', 'Repair', 'Outdated', 'Bricked');

CREATE TABLE T_TEAM
(
    id               BIGINT    DEFAULT random() PRIMARY KEY,
    name             varchar(30) NOT NULL,
    product          varchar(100),
    created_at       TIMESTAMP DEFAULT now(),
    modified_at      TIMESTAMP,
    default_location VARCHAR(6) check (default_location = 'PORTO' OR default_location = 'LISBON' OR
                                       default_location = 'BRAGA')
);
CREATE SEQUENCE IF NOT EXISTS SEQ_TEAM_ID;

CREATE TABLE T_TEAM_MEMBER
(
    id          BIGINT PRIMARY KEY,
    team_id     BIGINT      NOT NULL,
    ctw_id      varchar(8)  NOT NULL,
    name        varchar(60) NOT NULL,
    created_at  TIMESTAMP DEFAULT now(),
    modified_at TIMESTAMP,
    FOREIGN KEY (team_id) REFERENCES T_TEAM (id)
);
CREATE SEQUENCE IF NOT EXISTS SEQ_TEAM_MEMBER_ID;

CREATE TABLE T_RACK
(
    id               BIGINT    DEFAULT random() PRIMARY KEY,
    serial_number    varchar(20)      NOT NULL UNIQUE,
    status           rack_status_enum NOT NULL,
    team_id          BIGINT           NOT NULL REFERENCES T_TEAM (id),
    default_location VARCHAR(6) check (default_location = 'PORTO' OR default_location = 'LISBON' OR
                                       default_location = 'BRAGA'),
    assembled_at     DATE,
    created_at       TIMESTAMP DEFAULT now(),
    modified_at      TIMESTAMP
);
CREATE SEQUENCE IF NOT EXISTS SEQ_RACK_ID;

CREATE TABLE T_RACK_ASSET
(
    id        BIGINT DEFAULT random() PRIMARY KEY,
    asset_tag varchar(10) NOT NULL UNIQUE,
    rack_id   BIGINT      NOT NULL,
    FOREIGN KEY (rack_id) REFERENCES T_RACK (id)
);
CREATE SEQUENCE IF NOT EXISTS SEQ_RACK_ASSET_ID;

CREATE TABLE T_BOOKING
(
    id           BIGINT    DEFAULT random() PRIMARY KEY,
    rack_id      BIGINT    NOT NULL,
    requester_id BIGINT    NOT NULL,
    book_from    TIMESTAMP NOT NULL,
    book_to      TIMESTAMP NOT NULL,
    created_at   TIMESTAMP DEFAULT now(),
    modified_at  TIMESTAMP,
    FOREIGN KEY (rack_id) REFERENCES T_RACK (id),
    FOREIGN KEY (requester_id) REFERENCES T_TEAM_MEMBER (id)
);
CREATE SEQUENCE IF NOT EXISTS SEQ_BOOKING_ID;



