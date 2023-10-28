package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {
}
