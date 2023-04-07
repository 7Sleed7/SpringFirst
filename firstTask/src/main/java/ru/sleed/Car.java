package ru.sleed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {
    @Autowired
    private CarEngine carEngine;

    public void getEngineModel() {
        System.out.println(carEngine.getEngineType());
    }
}
