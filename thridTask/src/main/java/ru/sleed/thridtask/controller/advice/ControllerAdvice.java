package ru.sleed.thridtask.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ru.sleed.thridtask.exeption.ApplicationException;
import ru.sleed.thridtask.model.dto.ExceptionDto;

@Slf4j
@RestController
public class ControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<String> handler(ApplicationException applicationException) {
        ExceptionDto exception = ExceptionDto.exceptionToDto(applicationException);
        log.error(exception.getExceptionMessage(), exception);
        return new ResponseEntity<>(exception.getExceptionMessage(), exception.getStatus());
    }
}
