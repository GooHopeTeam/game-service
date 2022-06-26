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

    private static final Map<String, String> detailByConstraint = new HashMap<>();

    static {
        detailByConstraint.put("Positive", "less_than_1");
        detailByConstraint.put("PositiveOrZero", "less_then_0");
        detailByConstraint.put("NotBlank", "required");
    }

    public static String getDetailByConstraint(String constraintName) {
        return detailByConstraint.get(constraintName);
    }
}
