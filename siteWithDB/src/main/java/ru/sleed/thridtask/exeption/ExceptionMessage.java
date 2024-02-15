package ru.sleed.thridtask.exeption;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionMessage {
    ID_NOT_FOUND(HttpStatus.NOT_FOUND, "id not found"),
    NAME_NOT_FOUND(HttpStatus.NOT_FOUND, "username not found");

    private final HttpStatus httpStatus;
    private final String exceptionMessage;

    ExceptionMessage(HttpStatus status, String exceptionMessage) {
        this.httpStatus = status;
        this.exceptionMessage = exceptionMessage;
    }
}
