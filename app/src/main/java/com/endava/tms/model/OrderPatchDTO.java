package com.endava.tms.model;

public class OrderPatchDTO {
    Integer orderID;
    String ticketCategoryDescription;
    long nrTickets;

    public OrderPatchDTO(Integer orderID, String ticketCategoryDescription, long nrTickets) {
        this.orderID = orderID;
        this.ticketCategoryDescription = ticketCategoryDescription;
        this.nrTickets = nrTickets;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public String getTicketCategoryID() {
        return ticketCategoryDescription;
    }

    public void setTicketCategoryID(String ticketCategoryID) {
        this.ticketCategoryDescription = ticketCategoryID;
    }

    public long getNrTickets() {
        return nrTickets;
    }

    public void setNrTickets(long nrTickets) {
        this.nrTickets = nrTickets;
    }

    @Override
    public String toString() {
        return "OrderPatchDTO{" +
                "orderID=" + orderID +
                ", ticketCategoryID=" + ticketCategoryDescription +
                ", nrTickets=" + nrTickets +
                '}';
    }
}
