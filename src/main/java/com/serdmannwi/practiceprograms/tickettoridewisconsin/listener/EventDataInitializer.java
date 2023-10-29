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
            eventRepository.save(new Event("Test Event 1", TEST_EVENT_ONE_ID, "Test Event 1",
                "Test Event 1 Description", "Test Event 1 Resolved",
                "Test Event 1 Expired", EventType.SINGLEPLAYER_IMMEDIATE, false, false,
                0, 0));
            eventRepository.save(new Event("Test Event 2", TEST_EVENT_TWO_ID, "Test Event 2",
                "Test Event 2 Description", "Test Event 2 Resolved",
                "Test Event 2 Expired", EventType.SINGLEPLAYER_MULTITURN, false, false,
                5, 5));
            eventRepository.save(new Event("Test Event 3", TEST_EVENT_THREE_ID, "Test Event 3",
                "Test Event 3 Description", "Test Event 3 Resolved",
                "Test Event 3 Expired", EventType.MULTIPLAYER_IMMEDIATE, false, false,
                0, 0));
            eventRepository.save(new Event("Test Event 4", TEST_EVENT_FOUR_ID, "Test Event 3",
                "Test Event 3 Description", "Test Event 3 Resolved",
                "Test Event 3 Expired", EventType.MULTIPLAYER_MULTITURN, false, false,
                3, 3));
        }
    }
}
