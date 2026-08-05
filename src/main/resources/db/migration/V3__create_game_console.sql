CREATE TABLE game_console (
      game_id BIGINT NOT NULL,
      console_id BIGINT NOT NULL,

      CONSTRAINT fk_game
          FOREIGN KEY (game_id) REFERENCES games (id),

      CONSTRAINT fk_console
          FOREIGN KEY (console_id) REFERENCES consoles (id),

      CONSTRAINT unique_relation
          UNIQUE (game_id, console_id)
);