package com.endava.tms.model;

public class OrderPatchDTO {
    Integer orderID;
    Integer ticketCategoryID;
    long nrTickets;

    public OrderPatchDTO(Integer orderID, Integer ticketCategoryID, long nrTickets) {
        this.orderID = orderID;
        this.ticketCategoryID = ticketCategoryID;
        this.nrTickets = nrTickets;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getTicketCategoryID() {
        return ticketCategoryID;
    }

    public void setTicketCategoryID(Integer ticketCategoryID) {
        this.ticketCategoryID = ticketCategoryID;
    }

    public long getNrTickets() {
        return nrTickets;
    }

    public void setNrTickets(long nrTickets) {
        this.nrTickets = nrTickets;
    }
}
