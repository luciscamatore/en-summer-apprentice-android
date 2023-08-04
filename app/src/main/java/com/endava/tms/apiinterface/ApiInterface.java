package com.endava.tms.apiinterface;

import com.endava.tms.model.Event;
import com.endava.tms.model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("getAllEvents")
    Call<List<Event>> getAllEvents();

    @GET("getAllOrders")
    Call<List<Order>> getAllOrders();
}
