package ru.sleed.thridtask.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.sleed.thridtask.exeption.ApplicationException;
import ru.sleed.thridtask.model.dto.ExceptionDto;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<String> handler(ApplicationException applicationException) {
        ExceptionDto exceptionDto = ExceptionDto.exceptionToDto(applicationException);
        log.error(exceptionDto.getExceptionMessage(), exceptionDto);
        return new ResponseEntity<>(exceptionDto.getExceptionMessage(), exceptionDto.getStatus());
    }
}
