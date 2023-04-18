package ru.sleed.thridtask.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import ru.sleed.thridtask.exeption.ApplicationException;

@Data
@AllArgsConstructor
public class ExceptionDto extends Throwable{
    private String exceptionMessage;
    private HttpStatus status;

    public static ExceptionDto exceptionToDto(ApplicationException applicationException) {
        return new ExceptionDto(
                applicationException.getAppException().getMessage(),
                applicationException.getAppException().getHttpStatus()
        );
    }
}

