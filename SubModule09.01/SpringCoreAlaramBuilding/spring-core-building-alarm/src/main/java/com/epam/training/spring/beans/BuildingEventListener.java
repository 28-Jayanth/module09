package com.epam.training.spring.beans;

import com.epam.training.spring.model.Alarm;
import com.epam.training.spring.model.BuildingEvent;
import com.epam.training.spring.model.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class BuildingEventListener implements ApplicationListener<BuildingEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(BuildingEventListener.class);

    @Override
    public void onApplicationEvent(BuildingEvent event) {
        Alarm alarm = createAlarmFromEvent(event);
        if (alarm != null) {
            LOG.warn("Alarm generated: {}", alarm);
        }
    }

    private Alarm createAlarmFromEvent(BuildingEvent event) {
        return switch (event.getEventType()) {
            case FIRE_DETECTION -> new Alarm("EVACUATE", event.getLocation(), "Fire detected!");
            case TEMPERATURE_TOO_HIGH -> new Alarm("COOL_DOWN", event.getLocation(), "Temperature too high");
            case TEMPERATURE_TOO_LOW -> new Alarm("HEAT_UP", event.getLocation(), "Temperature too low");
            case DOOR_OPENED -> new Alarm("SECURE", event.getLocation(), "Door was opened");
            case DOOR_CLOSED -> null; // no alarm
        };
    }
}