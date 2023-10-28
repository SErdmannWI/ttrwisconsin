package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerRecord, String> {
}
