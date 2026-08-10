package maraisaferreira.com.github.RetroGamePlatform.helpers;

import maraisaferreira.com.github.RetroGamePlatform.model.enums.GameType;

import java.util.Arrays;

public final class Messages {

    public static final String invalidGameTypeMessage =
            "Invalid gameType. Values allowed: " + Arrays.toString(GameType.values());


    public static final String anyCorrectId = "Any ID was correctly sent. Try again.";

    public static final String notNullOrEmpty = "This field cannot be null or empty.";
    public static final String maxValue = "Maximum value allowed: ";
    public static final String minValue = "Lower value allowed: ";
    public static final String atLestOneId = "You must send at least one ID.";
    public static final String maxLength = "Maximum length allowed: ";
    public static final String atLeastOneField = "No data to update. At least one field must be filled.";


    public static String notFound(String objName) {
        return String.format("%s not found. Is ID correct?", objName);
    }

    public static String getUniqueFieldMessage(String field){
        return String.format("This %s already exists in the database. The %s must be unique.", field, field);
    }

    private Messages(){
    }
}
