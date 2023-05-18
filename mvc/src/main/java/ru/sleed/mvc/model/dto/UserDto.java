package ru.sleed.mvc.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.*;
import ru.sleed.mvc.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public static UserDto userToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getDateOfBirth()
        );
    }

    public static User dtoToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getDateOfBirth()
        );
    }

    public static List<UserDto> UserListToDto(List<User> users) {
        return users.stream()
                .map(UserDto::userToDto)
                .collect(Collectors.toList());
    }
}
