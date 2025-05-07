package com.epam.training.spring.beans;

import com.epam.training.spring.beans.BuildingEventReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Application {

    @Autowired
    private BuildingEventReader reader;

    public void run() {
        reader.readEvents();
    }
}