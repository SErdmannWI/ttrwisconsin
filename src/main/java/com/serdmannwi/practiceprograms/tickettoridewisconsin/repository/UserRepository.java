package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserRecord, String> {
}
