package com.endava.tms.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Event {
    Integer eventID;
    Venue venue;
    String eventType;
    String eventDescription;
    String eventName;
    String startDate;
    String endDate;
    List<TicketCategory> ticketCategory;
    Integer eventImage;

    public Event(Integer eventID, Venue venue, String eventType, String eventDescription, String eventName, String startDate, String endDate, List<TicketCategory> ticketCategory) {
        this.eventID = eventID;
        this.venue = venue;
        this.eventType = eventType;
        this.eventDescription = eventDescription;
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ticketCategory = ticketCategory;
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<TicketCategory> getTicketCategory() {
        return ticketCategory;
    }

    public void setTicketCategory(List<TicketCategory> ticketCategory) {
        this.ticketCategory = ticketCategory;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventID=" + eventID +
                ", venue=" + venue +
                ", eventType='" + eventType + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", eventName='" + eventName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", ticketCategory=" + ticketCategory +
                '}';
    }
}
