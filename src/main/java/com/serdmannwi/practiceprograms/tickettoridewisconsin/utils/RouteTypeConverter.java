package com.serdmannwi.practiceprograms.tickettoridewisconsin.utils;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.RouteType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter(autoApply = true)
public class RouteTypeConverter implements AttributeConverter<RouteType, String> {
    @Override
    public String convertToDatabaseColumn(RouteType attribute) {
        return (attribute == null) ? null : attribute.getValue();
    }

    @Override
    public RouteType convertToEntityAttribute(String dbData) {
        return Arrays.stream(RouteType.values())
            .filter(routeType -> routeType.getValue().equals(dbData))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Could not match with known RouteType: " + dbData));
    }
}
