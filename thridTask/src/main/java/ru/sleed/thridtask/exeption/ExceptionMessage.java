package ru.sleed.thridtask.exeption;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionMessage {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found");

    private final HttpStatus httpStatus;
    private final String message;

    ExceptionMessage(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
