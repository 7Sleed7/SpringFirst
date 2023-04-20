package ru.sleed.thridtask.exeption;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionMessage {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found");

    private final HttpStatus httpStatus;
    private final String exceptionMessage;

    ExceptionMessage(HttpStatus status, String exceptionMessage) {
        this.httpStatus = status;
        this.exceptionMessage = exceptionMessage;
    }
}
