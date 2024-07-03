DROP TABLE IF EXISTS T_RACK_ASSET;
DROP TABLE IF EXISTS T_RACK;
DROP TABLE IF EXISTS T_TEAM;

CREATE TABLE T_RACK
(
    id               UUID      DEFAULT gen_random_uuid() PRIMARY KEY,
    serial_number    varchar(20) NOT NULL UNIQUE,
    team_id          UUID        NOT NULL,
    created_at       TIMESTAMP DEFAULT now(),
    default_location VARCHAR(10)
);

CREATE TABLE T_RACK_ASSET
(
    id        UUID DEFAULT gen_random_uuid(),
    asset_tag varchar(10) NOT NULL,
    rack_id   UUID        NOT NULL,
    UNIQUE (asset_tag),
    PRIMARY KEY (id),
    FOREIGN KEY (rack_id) REFERENCES T_RACK (id)
);

CREATE TABLE T_TEAM
(
    id               UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name             varchar(30) NOT NULL,
    default_location varchar(10)
);
