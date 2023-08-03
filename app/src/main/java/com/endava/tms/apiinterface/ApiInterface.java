package com.endava.tms.apiinterface;

import com.endava.tms.model.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("getAllEvents")
    Call<List<Event>> getAllEvents();
}
