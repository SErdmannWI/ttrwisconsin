package com.serdmannwi.practiceprograms.tickettoridewisconsin.service;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.Event;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {
    //TODO Events will need to be stored in a Deque? Need to simulate a shuffled deck of event cards

    private final EventRepository eventRepository; // Master Repository for all Events in game. Use for creating initial "deck"
    private Deque<Event> eventDeck; //Simulated Deck of Event cards
    private Map<String, List<Event>> playerFinishedEvents; //Key playerID, value List of Events that a Player has "completed"

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
        eventDeck = new ArrayDeque<>();
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public void createDeck() {
        List<Event> allEvents = eventRepository.findAll();
        Collections.shuffle(allEvents);
        eventDeck.addAll(allEvents);
    }

    public void createPlayerEventMap(String[] playerIds) {
        for (String id : playerIds) {
            playerFinishedEvents.put(id, new ArrayList<>());
        }
    }

    /**
     * Gets Event at top of Deque and moves it to the bottom
     * @return Top Event
     */
    public Event getNextEvent() {
        Event currentEvent = eventDeck.poll();
        eventDeck.addLast(currentEvent);

        return currentEvent;
    }

    public void addFinishedEventToPlayer(String playerId, Event event) {
        playerFinishedEvents.get(playerId).add(event);
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

    /**
     * If there are any active Events, this will decrement the turnsRemaining by 1 and check if the event has expired.
     * If the Event has expired, it will return the List of expired Events.
     * @return
     */
    public List<Event> incrementEventTimer() {
        return new ArrayList<>();
    }
}
