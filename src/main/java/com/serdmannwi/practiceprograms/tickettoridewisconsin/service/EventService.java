package com.serdmannwi.practiceprograms.tickettoridewisconsin.service;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.Event;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getAllActiveEvents() {
        return eventRepository.findAll().stream()
            .filter(Event::isActive)
            .collect(Collectors.toList());
    }

    public Event getEventById(String id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event activateEvent(String id) {
        Optional<Event> inactiveEventOptional = eventRepository.findById(id);
        if (inactiveEventOptional.isEmpty()) {
            return null;
        }
        Event inactiveEvent = inactiveEventOptional.get();
        inactiveEvent.setActive(true);

        return eventRepository.save(inactiveEvent);
    }

    public Event deactivateEvent(String id) {
        Optional<Event> inactiveEventOptional = eventRepository.findById(id);
        if (inactiveEventOptional.isEmpty()) {
            return null;
        }
        Event inactiveEvent = inactiveEventOptional.get();
        inactiveEvent.setActive(false);

        return eventRepository.save(inactiveEvent);
    }
}
