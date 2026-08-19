CREATE TABLE players
(
    id         binary(16) PRIMARY KEY,
    name       VARCHAR(250) NOT NULL,
    email      VARCHAR(250) NOT NULL UNIQUE,
    birth_date DATE         NOT NULL,
    password   VARCHAR(255) NOT NULL
);