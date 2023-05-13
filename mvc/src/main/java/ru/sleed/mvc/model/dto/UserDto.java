package ru.sleed.mvc.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    @Positive
    private Long id;
    @NotEmpty
    private String username;
    @PastOrPresent
    private LocalDate dateOfBirth;


}
