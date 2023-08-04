package com.endava.tms.model;

public class Order {

    String eventName;
    int nrTickets;
    int price;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getNrTickets() {
        return nrTickets;
    }

    public void setNrTickets(int nrTickets) {
        this.nrTickets = nrTickets;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "eventName='" + eventName + '\'' +
                ", nrTickets=" + nrTickets +
                ", price=" + price +
                '}';
    }
}
