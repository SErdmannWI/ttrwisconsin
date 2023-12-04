package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.effect;

import jakarta.persistence.Column;

public class ActiveConditionalEffect extends Effect {
    @Column(columnDefinition = "TEXT")
    private String conditionIdsJson;
    @Column(columnDefinition = "TEXT")
    private String resourcesAffectedJson;
    @Column(columnDefinition = "TEXT")
    private String actionsAffectedJson;
}
