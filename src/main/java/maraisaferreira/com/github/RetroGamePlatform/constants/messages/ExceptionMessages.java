package maraisaferreira.com.github.RetroGamePlatform.constants.messages;

import maraisaferreira.com.github.RetroGamePlatform.model.enums.GameType;

import java.util.Arrays;

public final class ExceptionMessages {
    public static final String INVALID_GAME_TYPE =
            "Invalid gameType. Values allowed: " + Arrays.toString(GameType.values());

    public static final String ANY_CORRECT_ID = "Any ID was correctly sent. Try again.";

    public static String notFound(String register) {
        return String.format("%s not found. Is ID correct?", register);
    }

    public static String getUniqueFieldMessage(String field) {
        return String.format("This %s already exists in the database. The %s must be unique.", field, field);
    }

    private ExceptionMessages() {

    }
}
