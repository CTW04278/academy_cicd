CREATE TABLE Car
(
    vin           VARCHAR(20) PRIMARY KEY,
    brand         VARCHAR(10),
    model         VARCHAR(10),
    license_plate VARCHAR(8),
    fuel          VARCHAR(10),
    stand_id      BIGINT,
    CONSTRAINT fk_stand
        FOREIGN KEY (stand_id)
            REFERENCES Stand (id)
);
