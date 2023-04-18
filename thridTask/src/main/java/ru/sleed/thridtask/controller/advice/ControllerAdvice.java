package ru.sleed.thridtask.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ru.sleed.thridtask.exeption.ApplicationException;
import ru.sleed.thridtask.exeption.ExceptionMessage;
import ru.sleed.thridtask.model.dto.ExceptionDto;

@Slf4j
@RestController
public class ControllerAdvice {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<String> handler() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
