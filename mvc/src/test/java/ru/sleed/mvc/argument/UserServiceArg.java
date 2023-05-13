package ru.sleed.mvc.argument;

import org.junit.jupiter.params.provider.Arguments;
import ru.sleed.mvc.provider.UserProvider;


import java.util.stream.Stream;

public class UserServiceArg {

    public static Stream<Arguments> getCreateUserDtoArgs() {
        return Stream.of(
                Arguments.of(
                        UserProvider.getCreateUserDto("test"),
                        UserProvider.getUserDto("testuser")
                ),
                Arguments.of(
                        UserProvider.getCreateUserDto("user"),
                        UserProvider.getUserDto("user")
                )
        );
    }
}
