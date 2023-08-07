package com.endava.tms.apiinterface;

import com.endava.tms.model.Event;
import com.endava.tms.model.Order;
import com.endava.tms.model.OrderPatchDTO;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("getAllEvents")
    Call<List<Event>> getAllEvents();

    @GET("getAllOrders")
    Call<List<Order>> getAllOrders();

    @DELETE("deleteOrder/{orderID}")
    Call<Void> deleteOrder(@Path("orderID") Integer orderID);

    @PATCH("patchOrder/")
    Call<Void> patchOrder(@Body OrderPatchDTO orderDTO);
}
