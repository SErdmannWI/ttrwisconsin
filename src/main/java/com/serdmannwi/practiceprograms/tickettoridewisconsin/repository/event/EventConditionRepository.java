package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventConditionRepository extends JpaRepository<Event.EventCondition, String> {
}
