package com.serdmannwi.practiceprograms.tickettoridewisconsin.listener;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.ActionConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.EventConditionConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.EventConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class EventDataInitializer {

    private EventRepository eventRepository;
    private EventConditionRepository conditionRepository;

    @Autowired
    public EventDataInitializer(EventRepository eventRepository, EventConditionRepository eventConditionRepository) {
        this.eventRepository = eventRepository;
        this.conditionRepository = eventConditionRepository;
    }

    @PostConstruct
    public void init() {
        //Create and save Passive Events
        SinglePlayerPassiveEvent bailoutEvent = new SinglePlayerPassiveEvent(EventConstants.BAILOUT_NAME,
            EventConstants.BAILOUT_ID, EventConstants.BAILOUT_DESC);
        eventRepository.save(bailoutEvent);

        //Create Active Events without Conditions
        SinglePlayerActiveEvent derailmentEvent = new SinglePlayerActiveEvent(EventConstants.DERAILMENT_NAME,
            EventConstants.DERAILMENT_ID, EventConstants.DERAILMENT_DESC, ActionConstants.RECEIVE_FREIGHT_CONTRACT_POINTS_ID);
        eventRepository.save(derailmentEvent);

        //Create Conditions with reference to Event
        EventCondition eventCondition = new EventCondition(EventConditionConstants.DERAILMENT_CONDITION_ID, derailmentEvent,
            EventConditionConstants.DERAILMENT_CONDITION_ID);
        conditionRepository.save(eventCondition);

        //Set reference from Event to Condition
        derailmentEvent.setEventCondition(eventCondition);
        eventRepository.save(derailmentEvent);
    }


}
