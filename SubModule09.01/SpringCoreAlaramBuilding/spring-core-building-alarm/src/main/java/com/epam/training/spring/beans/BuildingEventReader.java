package com.epam.training.spring.beans;

import com.epam.training.spring.model.BuildingEvent;
import com.epam.training.spring.model.EventDto;
import com.epam.training.spring.model.EventType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.time.OffsetDateTime;
import java.util.List;

@Component
public class BuildingEventReader implements ApplicationEventPublisherAware {
    private static final Logger LOG = LoggerFactory.getLogger(BuildingEventReader.class);

    @Value("${event.file.path}")
    private String eventFilePath;

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @PostConstruct
    public void checkInputFile() {
        LOG.info("Checking access to input file: {}", eventFilePath);
        if (getClass().getClassLoader().getResourceAsStream(eventFilePath) == null) {
            throw new IllegalArgumentException("File not found: " + eventFilePath);
        }
    }

    public void readEvents() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(eventFilePath)) {
            ObjectMapper mapper = new ObjectMapper();
            List<EventDto> dtos = mapper.readValue(is, new TypeReference<List<EventDto>>() {});
            for (EventDto dto : dtos) {
                BuildingEvent event = new BuildingEvent(this);
                event.setEventType(dto.getEventType());
                event.setLocation(dto.getLocation());
                event.setMessage(dto.getMessage());
                event.setTime(dto.getTime());
                publisher.publishEvent(event);
            }
        } catch (IOException e) {
            LOG.error("Error reading or parsing events: ", e);
        }
    }
}