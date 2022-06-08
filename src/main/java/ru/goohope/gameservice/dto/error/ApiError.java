package ru.goohope.gameservice.dto.error;

public class ApiError {

    private final String code;

    public ApiError(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
