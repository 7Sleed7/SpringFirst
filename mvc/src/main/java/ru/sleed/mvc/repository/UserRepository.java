package ru.sleed.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sleed.mvc.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findUserById(Long id);

    boolean existsById(Long id);

    List<User> findByUsername(String surname);

}
