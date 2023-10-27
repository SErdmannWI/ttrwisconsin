package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TestRecord {

    @Id
    private String id;
    private String name;

    public TestRecord() {}

    public TestRecord(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
