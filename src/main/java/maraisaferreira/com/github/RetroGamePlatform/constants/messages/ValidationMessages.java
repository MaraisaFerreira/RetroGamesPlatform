package maraisaferreira.com.github.RetroGamePlatform.constants.messages;

public final class ValidationMessages {

    public static final String NOT_NULL_OR_EMPTY = "This field cannot be null or empty.";
    public static final String SEND_AT_LEAST_ONE_ID = "You must send at least one ID.";
    public static final String MAX_ACRONYM_LENGTH = "Acronym must be up to 20 characters.";
    public static final String MAX_ORIGIN_LENGTH = "Origin must be up to 100 characters.";
    public static final String MIN_PASSWORD_LENGTH = "The password must be at least 5 characters.";
    public static final String NO_DATA_TO_UPDATE = "No data to update. At least one field must be filled.";
    public static final String NOT_A_VALID_EMAIL = "This is not a valid email.";
    public static final String INVALID_DATE = "Invalid date! Birthdate must be in the past.";
    public static final String INVALID_AGE = "You have to be at least 10 years old to register.";

    public static final String GAME_YEAR = "Year must be between 1972 and 2010.";

    private ValidationMessages() {
    }
}
