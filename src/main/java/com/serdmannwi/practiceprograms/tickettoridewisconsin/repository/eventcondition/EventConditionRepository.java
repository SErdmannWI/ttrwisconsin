package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.eventcondition;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventConditionRepository extends JpaRepository<EventCondition, String> {
}
