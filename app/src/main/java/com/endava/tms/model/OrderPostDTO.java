package com.endava.tms.model;

public class OrderPostDTO {
    Integer customerID;
    Integer eventID;
    String ticketCategoryDescription;
    int numberOfTickets;

    public OrderPostDTO(Integer customerID, Integer eventID, String ticketCategoryDescription, int numberOfTickets) {
        this.customerID = customerID;
        this.eventID = eventID;
        this.ticketCategoryDescription = ticketCategoryDescription;
        this.numberOfTickets = numberOfTickets;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public String getTicketCategoryDescription() {
        return ticketCategoryDescription;
    }

    public void setTicketCategoryDescription(String ticketCategoryDescription) {
        this.ticketCategoryDescription = ticketCategoryDescription;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    @Override
    public String toString() {
        return "OrderPostDTO{" +
                "eventID=" + eventID +
                ", ticketCategoryDescription='" + ticketCategoryDescription + '\'' +
                ", numberOfTickets=" + numberOfTickets +
                '}';
    }
}
