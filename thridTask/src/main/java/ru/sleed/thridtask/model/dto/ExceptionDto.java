package ru.sleed.thridtask.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.sleed.thridtask.exeption.ExceptionMessage;

@Data
@Builder
@AllArgsConstructor
public class ExceptionDto {
    private ExceptionMessage exceptionMessage;
    private String message;
}

