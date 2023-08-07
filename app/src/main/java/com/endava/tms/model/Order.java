package com.endava.tms.model;

import com.google.gson.annotations.SerializedName;

public class Order {
    int orderID;
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
                ", orderEventName='" + orderEventName + '\'' +
                ", nrTickets=" + nrTickets +
                ", price=" + price +
                '}';
    }
}
