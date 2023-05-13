package ru.sleed.mvc.repository;

import org.springframework.stereotype.Repository;
import ru.sleed.mvc.model.dto.UserDto;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<UserDto> findUserById(Long id);

    boolean existById(Long id);

    void save(UserDto userDto);


    List<UserDto> getAllUsers();
}
