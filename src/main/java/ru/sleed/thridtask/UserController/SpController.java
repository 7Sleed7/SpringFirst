package ru.sleed.thridtask.UserController;

import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.web.bind.annotation.*;
import ru.sleed.thridtask.entity.User;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users") //fix
public class SpController {
    private final List<User> users = new ArrayList<>();

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id)).findFirst();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        users.add(user);
        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        User existingUser = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow();

        existingUser.setName(user.getName());
        existingUser.setAge(user.getAge());

        return existingUser;
    }

    @PatchMapping("/{id}")
    public Optional<User> patchUser(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Optional<User> existingUser = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    existingUser.orElseThrow().setName((String) value);
                    break;
                case "age":
                    existingUser.orElseThrow().setAge((Integer) value);
                    break;
            }
        });

        return existingUser;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        users.removeIf(u -> u.getId().equals(id));
    }
}
