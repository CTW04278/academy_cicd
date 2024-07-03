CREATE TABLE T_RACK
(
    id               UUID DEFAULT gen_random_uuid(),
    serial_number    text NOT NULL,
    team_id          UUID NOT NULL,
    default_location varchar(10)
);

CREATE TABLE T_RACK_ASSET
(
    id        UUID DEFAULT gen_random_uuid(),
    asset_tag varchar(10) NOT NULL,
    rack_id   UUID        NOT NULL
);

CREATE TABLE T_TEAM
(
    id               UUID DEFAULT gen_random_uuid(),
    name             varchar(30) NOT NULL,
    default_location varchar(10)
);
