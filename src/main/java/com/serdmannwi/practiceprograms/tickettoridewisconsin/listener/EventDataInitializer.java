package com.serdmannwi.practiceprograms.tickettoridewisconsin.listener;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.EventConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.event.EventRepository;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.event.SinglePlayerPassiveEvent;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class EventDataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private EventRepository eventRepository;

    @Autowired
    public EventDataInitializer(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        SinglePlayerPassiveEvent bailoutEvent = new SinglePlayerPassiveEvent(EventConstants.BAILOUT_NAME,
            EventConstants.BAILOUT_ID, EventConstants.BAILOUT_DESC, EventConstants.BAILOUT_CONDITION);
        eventRepository.save(bailoutEvent);
    }


}
