package ru.sleed.thridtask.exeption;

import lombok.Data;

@Data
public class ApplicationException extends RuntimeException {

    private ExceptionMessage appException;

    public ApplicationException(ExceptionMessage exceptionMessage){
        super();
        this.appException = exceptionMessage;
    }
}
