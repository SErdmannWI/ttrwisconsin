package com.serdmannwi.practiceprograms.tickettoridewisconsin.utils;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.EventType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter(autoApply = true)
public class EventTypeConverter implements AttributeConverter<EventType, String> {

    @Override
    public String convertToDatabaseColumn(EventType attribute) {
        return (attribute == null) ? null : attribute.getValue();
    }

    @Override
    public EventType convertToEntityAttribute(String dbData) {
        return Arrays.stream(EventType.values())
            .filter(eventType -> eventType.getValue().equals(dbData))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Could not match with known EventType: " + dbData));
    }
}
