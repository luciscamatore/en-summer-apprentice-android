package com.endava.tms.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event {
    String eventName;
    String eventDescription;
    String venueLocation;
    String eventType;
    LocalDate startDate;
    LocalDate endDate;
    Integer eventImage;

    public Event(String eventName, String eventDescription, String venueLocation, String eventType, LocalDate startDate, LocalDate endDate, Integer eventImage) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.venueLocation = venueLocation;
        this.eventType = eventType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventImage = eventImage;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getVenueLocation() {
        return venueLocation;
    }

    public void setVenueLocation(String venueLocation) {
        this.venueLocation = venueLocation;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getEventImage() {
        return eventImage;
    }

    public void setEventImage(Integer eventImage) {
        this.eventImage = eventImage;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + eventName + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", venueLocation='" + venueLocation + '\'' +
                ", eventType='" + eventType + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", eventImage=" + eventImage +
                '}';
    }
}
