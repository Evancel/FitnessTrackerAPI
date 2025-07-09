package fitnesstracker.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AppCategory {
    BASIC("basic"),
    PREMIUM("premium");

    private String value;

    AppCategory(String value) {
        this.value = value;
    }

    @JsonCreator
    public static AppCategory convertToEnum(String input) {
        try {
            return AppCategory.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid application category: " + input);
        }
    }
}
