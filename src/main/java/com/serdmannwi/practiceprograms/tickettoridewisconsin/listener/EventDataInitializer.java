package com.serdmannwi.practiceprograms.tickettoridewisconsin.listener;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.Event;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.EventRepository;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class EventDataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private EventRepository eventRepository;
    private final String TEST_EVENT_ONE_ID = "Event1";
    private final String TEST_EVENT_TWO_ID = "Event2";
    private final String TEST_EVENT_THREE_ID = "Event3";
    private final String TEST_EVENT_FOUR_ID = "Event4";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (eventRepository.count() == 0) {
            eventRepository.save(new Event("Test Event 1", TEST_EVENT_ONE_ID, "Test Event 1", "Test Effect ID",
                "Test Event 1 Description", "Test Event 1 Resolved",
                "Test Event 1 Expired", EventType.SINGLEPLAYER_IMMEDIATE, false, false,
                0, 0));
        }
    }
}
