package com.serdmannwi.practiceprograms.tickettoridewisconsin.service.model;

public class Action {
    private String actionName;
    private String actionId;
    private String actionDescription;
    private boolean isAvailable;
    private boolean hasFollowUp; //If an Action does not simulate anything in-game (e.g. the card deck is not currently simulated) then hasFollowUp should be false.

    public Action(String actionName, String actionId, String actionDescription, boolean isAvailable, boolean hasFollowUp) {
        this.actionName = actionName;
        this.actionId = actionId;
        this.actionDescription = actionDescription;
        this.isAvailable = isAvailable;
        this.hasFollowUp = hasFollowUp;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isHasFollowUp() {
        return hasFollowUp;
    }

    public void setHasFollowUp(boolean hasFollowUp) {
        this.hasFollowUp = hasFollowUp;
    }
}
