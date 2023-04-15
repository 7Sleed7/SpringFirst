package ru.sleed.thridtask.model.exeption;

import lombok.Data;

@Data
public class AppException extends RuntimeException {

    private ExceptionMessage appException;

    public AppException(ExceptionMessage exceptionMessage){
        this.appException = exceptionMessage;
    }
}
