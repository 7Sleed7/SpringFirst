package ru.sleed.thridtask.model.exeption;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionMessage {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found");

    private final HttpStatus httpStatus;
    private final String desc;

    ExceptionMessage(HttpStatus httpStatus, String desc) {
        this.httpStatus = httpStatus;
        this.desc = desc;
    }
}
