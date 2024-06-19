CREATE TABLE T_TEAM_MEMBER
(
    id      UUID PRIMARY KEY,
    team_id UUID        NOT NULL,
    ctw_id  varchar(8)  NOT NULL,
    name    varchar(60) NOT NULL,
    FOREIGN KEY (team_id) REFERENCES T_TEAM (id)
);

CREATE TABLE T_BOOKING
(
    id           BIGINT DEFAULT random() PRIMARY KEY,
    rack_id      UUID      NOT NULL,
    requester_id UUID      NOT NULL,
    book_from    TIMESTAMP NOT NULL,
    book_to      TIMESTAMP NOT NULL,
    FOREIGN KEY (rack_id) REFERENCES T_RACK (id),
    FOREIGN KEY (requester_id) REFERENCES T_TEAM_MEMBER (id)
);
