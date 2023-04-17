package ru.sleed.thridtask.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private Integer age;
}
