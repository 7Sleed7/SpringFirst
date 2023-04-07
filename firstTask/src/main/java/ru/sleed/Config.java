package ru.sleed;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan
public class Config {
    @Bean("Car")
    public Car getCar() {
        return new Car();
    }
}
