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
    public ResponseEntity<ExceptionDto> handler(ApplicationException applicationException) {
        log.error("catch exception: ", applicationException);
        ExceptionDto exceptionDto = ExceptionDto
                .builder()
                .exceptionMessage(applicationException.getExceptionMessage())
                .message(applicationException.getExceptionMessage().getExceptionMessage())
                .build();
        log.error("return response : ExceptionDto={}", exceptionDto);
        return ResponseEntity.status(applicationException.getExceptionMessage().getHttpStatus()).body(exceptionDto);
    }
}