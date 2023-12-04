package com.serdmannwi.practiceprograms.tickettoridewisconsin.service;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.event.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;

public class EventServiceTest {

    private EventService eventService;
    @Mock
    private EventRepository eventRepository;

    @BeforeEach
    void setup() {
        eventRepository = mock(EventRepository.class);
        eventService = new EventService(eventRepository);
    }
}
