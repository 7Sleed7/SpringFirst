package ru.sleed.thridtask.exeption;

import lombok.Data;

@Data
public class ApplicationException extends RuntimeException {

    private ExceptionMessage exceptionMessage;

    public ApplicationException(ExceptionMessage exceptionMessage){
        super();
        this.exceptionMessage = exceptionMessage;
    }
}
