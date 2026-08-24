package maraisaferreira.com.github.RetroGamePlatform.constants.messages;

import maraisaferreira.com.github.RetroGamePlatform.model.enums.GameType;

import java.util.Arrays;

public final class ExceptionMessages {
    public static final String INVALID_GAME_TYPE =
            "Invalid gameType. Values allowed: " + Arrays.toString(GameType.values());

    public static final String ANY_CORRECT_ID = "Any ID was correctly sent. Try again.";
    public static final String UNDER_AGE = "Sorry, you have to be at least 10 years old.";

    public static final String CONSOLE_NOT_FOUND = "Console not found.";
    public static final String GAME_NOT_FOUND = "Game not found.";
    public static final String EMAIL_NOT_FOUND = "Email not found.";

    public static final String UNIQUE_NAME = "This name already exists. It must be unique.";
    public static final String UNIQUE_ACRONYM = "This acronym already exists. It must be unique.";
    public static final String UNIQUE_COVER = "This cover already exists. It must be unique.";
    public static final String UNIQUE_EMAIL = "This email already exists. It must be unique.";


    private ExceptionMessages() {

    }
}
