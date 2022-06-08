package ru.goohope.gameservice.dto.error;

import java.util.HashMap;
import java.util.Map;

public class ValidationError extends ApiError {

    private Map<String, String> details;

    public ValidationError() {
        super("validation_error");
        details = new HashMap<>();
    }

    public void addDetail(String name, String value) {
        details.put(name, value);
    }

    public Map<String, String> getDetails() {
        return details;
    }

    private static final Map<String, String> descriptionByConstraint = new HashMap<>();

    static {
        descriptionByConstraint.put("Positive", "less_than_1");
        descriptionByConstraint.put("PositiveOrZero", "less_then_0");
    }

    public static String getConstraintDescription(String constraintName) {
        return descriptionByConstraint.get(constraintName);
    }
}
