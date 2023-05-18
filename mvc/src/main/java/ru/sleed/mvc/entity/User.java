package ru.sleed.mvc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    @SequenceGenerator(name = "user_gen", sequenceName = "users_seq", allocationSize = 1)
    private Long id;
    @NotEmpty
    private String username;
    @PastOrPresent
    private LocalDate dateOfBirth;

    public User(Long id, String username, LocalDate dateOfBirth) {
        this.id = id;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
    }
}