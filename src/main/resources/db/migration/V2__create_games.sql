CREATE TABLE games (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    release_year INTEGER NOT NULL,
    game_type ENUM(
        'UNKNOWN',
        'PLATFORM',
        'ADVENTURE',
        'ARCADE',
        'ACTION',
        'FIGHTING',
        'RACE',
        'SHOOTER'
    ),
    cover_url VARCHAR(2018),

    CONSTRAINT ch_release_year_retro
       CHECK (release_year BETWEEN 1970 AND 2010)
);