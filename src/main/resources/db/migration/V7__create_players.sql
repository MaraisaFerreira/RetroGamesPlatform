CREATE TABLE players
(
    id       binary(16) PRIMARY KEY,
    name     VARCHAR(250) NOT NULL,
    email    VARCHAR(250) NOT NULL UNIQUE,
    age      INT          NOT NULL,
    password VARCHAR(255) NOT NULL,

    CONSTRAINT check_age
        CHECK (age BETWEEN 5 AND 100)
);