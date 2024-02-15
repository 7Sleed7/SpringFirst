package ru.sleed.thridtask.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Positive
    private Long id;
    @NotBlank
    private String name;
    @Positive
    private Integer age;

}
