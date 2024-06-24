CREATE TABLE Stand
(
    id      BIGINT PRIMARY KEY,
    name    VARCHAR NOT NULL,
    address VARCHAR(100)
);

CREATE SEQUENCE stand_id start 1;
