package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.EventResponse;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.Event;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.service.EventService;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/allEvents")
    public ResponseEntity<?> getAllEvents() {
        List<Event> allEvents = eventService.getAllEvents();
        if (allEvents.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse(404, "No Events were found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        }

        List<EventResponse> allEventResponses = allEvents.stream()
            .map(this::recordToResponse)
            .collect(Collectors.toList());

        return ResponseEntity.ok(allEventResponses);
    }

    @GetMapping("/allActiveEvents")
    public ResponseEntity<?> getAllActiveEvents() {
        List<Event> activeEvents = eventService.getAllActiveEvents();
        if (activeEvents.isEmpty()) {
            return ResponseEntity.ok().body("No active events");
        }
        return ResponseEntity.ok().body(activeEvents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable("id") String id) {
        Event event = eventService.getEventById(id);
        if (event == null) {
            ErrorResponse errorResponse = new ErrorResponse(404, "Could not find Event with ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        }

        return ResponseEntity.ok(recordToResponse(event));
    }

    @PutMapping("/createEventMap")
    public ResponseEntity<String> createEventMap(String[] playerIds) {
        eventService.createPlayerEventMap(playerIds);

        return ResponseEntity.ok().body("Created Event Map");
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<?> activateEvent(@PathVariable("id") String id) {
        Event event = eventService.getEventById(id);
        if (event == null) {
            ErrorResponse errorResponse = new ErrorResponse(404, "Could not find Event with ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        } else if (event.isActive()) {
            ErrorResponse errorResponse = new ErrorResponse(400, "Event is already active with ID: " + id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        }

        Event activatedEvent = eventService.activateEvent(id);

        if (!activatedEvent.isActive()) {
            ErrorResponse errorResponse = new ErrorResponse(400, "Event could not be activated with ID: " + id);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        }

        return ResponseEntity.ok(recordToResponse(activatedEvent));
    }

    @PutMapping("deactivate/{id}")
    public ResponseEntity<?> deactivateEvent(@PathVariable("id") String id) {
        Event event = eventService.getEventById(id);
        if (event == null) {
            ErrorResponse errorResponse = new ErrorResponse(404, "Could not find Event with ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        } else if (!event.isActive()) {
            ErrorResponse errorResponse = new ErrorResponse(400, "Event is already inactive with ID: " + id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        }

        Event deactivatedEvent = eventService.deactivateEvent(id);

        if (deactivatedEvent.isActive()) {
            ErrorResponse errorResponse = new ErrorResponse(400, "Event could not be deactivated with ID: " + id);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        }

        return ResponseEntity.ok(recordToResponse(deactivatedEvent));
    }

    @PutMapping("/incrementEventTimer")
    public ResponseEntity<String> incrementEventTimer() {
        List<Event> activeEvents = eventService.getAllActiveEvents();
        if (activeEvents.isEmpty()) {
            return ResponseEntity.ok().body("No Active Events");
        }
        return ResponseEntity.ok().body("");
    }

    /**----------------------------------------- Conversion Methods -----------------------------------------**/
    private EventResponse recordToResponse(Event event) {
        EventResponse eventResponse = new EventResponse();

        eventResponse.setName(event.getName());
        eventResponse.setEventId(event.getEventId());
        eventResponse.setCardDescription(event.getCardDescription());
//        eventResponse.setEffectDescription(event.getEffectDescription());
//        eventResponse.setResolutionMessage(event.getResolutionMessage());
//        eventResponse.setExpirationMessage(event.getExpirationMessage());
//        eventResponse.setEventType(event.getEventType());
//        eventResponse.setActive(event.isActive());
//        eventResponse.setHasExpired(event.hasExpired());
//        eventResponse.setTurnsRemaining(event.getTurnsRemaining());
//        eventResponse.setEventDuration(event.getEventDuration());

        return eventResponse;
    }
}
