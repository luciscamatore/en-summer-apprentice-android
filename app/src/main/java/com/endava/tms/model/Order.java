package com.endava.tms.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class Order {
    int orderID;
    @SerializedName("ticketCategoryDTO")
    TicketCategory ticketCategory;
    @SerializedName("eventName")
    String orderEventName;
    int nrTickets;
    int price;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public TicketCategory getTicketCategory() {
        return ticketCategory;
    }

    public void setTicketCategory(TicketCategory ticketCategory) {
        this.ticketCategory = ticketCategory;
    }

    public String getOrderEventName() {
        return orderEventName;
    }

    public void setOrderEventName(String orderEventName) {
        this.orderEventName = orderEventName;
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
                "orderID=" + orderID +
                ", ticketCategory=" + ticketCategory +
                ", orderEventName='" + orderEventName + '\'' +
                ", nrTickets=" + nrTickets +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderID == order.orderID;
    }


}
