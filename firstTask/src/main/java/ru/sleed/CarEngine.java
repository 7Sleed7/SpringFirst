package ru.sleed;

import org.springframework.stereotype.Component;

@Component
public class CarEngine {
    public String getEngineType() {
        return "3.0 TFSI";
    }
}
