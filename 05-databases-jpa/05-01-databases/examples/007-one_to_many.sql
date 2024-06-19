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
    id        UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    asset_tag varchar(10) NOT NULL UNIQUE,
    rack_id   UUID        NOT NULL
);


ALTER TABLE T_RACK_ASSET
    ADD CONSTRAINT FK_T_RACK
        FOREIGN KEY (rack_id) REFERENCES T_RACK;
