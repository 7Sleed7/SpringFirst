package ru.sleed.mvc.service;


import ru.sleed.mvc.model.dto.CreateUserDto;
import ru.sleed.mvc.model.dto.UpdateUserDto;
import ru.sleed.mvc.model.dto.UserDto;
import ru.sleed.mvc.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sleed.mvc.provider.UserProvider;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService service;


    @ParameterizedTest
    @MethodSource("ru.sleed.mvc.argument.UserServiceArg#getCreateUserDtoArgs")
    void createUser_whenValidUser_thanUser(CreateUserDto createUserDto,
                                           UserDto expectedUserDto) {
        UserDto actualUserDto = service.createUser(createUserDto);

        assertEquals(expectedUserDto, actualUserDto);
    }

    @ParameterizedTest
    @MethodSource("ru.sleed.mvc.argument.UserServiceArg#getCreateUserDtoArgs")
    void updateUser_whenValidUser_thanUser(UpdateUserDto updateUserDto,
                                           UserDto expectedUserDto,
                                           Long id){
        UserDto actualUserDto = service.updateUser(id, updateUserDto);

        assertEquals(expectedUserDto, actualUserDto);
    }


}
